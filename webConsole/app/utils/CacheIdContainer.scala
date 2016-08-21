package utils

import java.security.SecureRandom

import jp.t2v.lab.play2.auth._
import play.api.cache.CacheApi

import scala.annotation.tailrec
import scala.concurrent.duration._
import scala.reflect.ClassTag
import scala.util.Random

class CacheIdContainer[Id: ClassTag](cache: CacheApi) extends IdContainer[Id] {

  private val tokenSuffix = ":token"
  private val userIdSuffix = ":userId"
  private val random = new Random(new SecureRandom())

  def startNewSession(userId: Id, timeoutInSeconds: Int): AuthenticityToken = {
    removeByUserId(userId)
    val token = generate
    store(token, userId, timeoutInSeconds)
    token
  }

  @tailrec
  private final def generate: AuthenticityToken = {
    val table = "abcdefghijklmnopqrstuvwxyz1234567890_.~*'()"
    val token = Iterator.continually(random.nextInt(table.length)).map(table).take(64).mkString
    if (get(token).isDefined) generate else token
  }

  private def removeByUserId(userId: Id) {
    cache.get[String](userId.toString + userIdSuffix) foreach unsetToken
    unsetUserId(userId)
  }

  def remove(token: AuthenticityToken) {
    get(token) foreach unsetUserId
    unsetToken(token)
  }

  private def unsetToken(token: AuthenticityToken) {
    cache.remove(token + tokenSuffix)
  }
  private def unsetUserId(userId: Id) {
    cache.remove(userId.toString + userIdSuffix)
  }

  def get(token: AuthenticityToken) = cache.get(token + tokenSuffix).map(_.asInstanceOf[Id])

  private def store(token: AuthenticityToken, userId: Id, timeoutInSeconds: Int) {
    cache.set(token + tokenSuffix, userId, timeoutInSeconds.second)
    cache.set(userId.toString + userIdSuffix, token, timeoutInSeconds.second)
  }

  def prolongTimeout(token: AuthenticityToken, timeoutInSeconds: Int) {
    get(token).foreach(store(token, _, timeoutInSeconds))
  }

}
