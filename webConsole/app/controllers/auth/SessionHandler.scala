package controllers.auth

import java.security.SecureRandom

import com.github.uryyyyyyy.utils.Loggable
import jp.t2v.lab.play2.auth.{AuthenticityToken, IdContainer}

import scala.reflect.ClassTag
import scala.collection.mutable
import scala.util.Random

//本当はキャッシュサーバとか使う
class SessionHandler[Id: ClassTag](
  tokenMap: mutable.Map[Id, AuthenticityToken],
  tokenMap2: mutable.Map[AuthenticityToken, Id]
) extends IdContainer[Id] with Loggable {

  val random = new Random(new SecureRandom())


  //本当はtimeoutでexpire設定するべき
  override def startNewSession(userId: Id, timeoutInSeconds: Int): AuthenticityToken = {
    val oldToken = tokenMap.get(userId)
    if(oldToken.isDefined) tokenMap2.remove(oldToken.get)
    tokenMap.remove(userId)

    val newToken = "%064d".format(random.nextInt(1000000))
    tokenMap.put(userId, newToken)
    tokenMap2.put(newToken, userId)
    newToken
  }

  override def remove(token: AuthenticityToken): Unit = {
    val id = tokenMap2.get(token)
    if(id.isEmpty) return

    tokenMap.remove(id.get)
    tokenMap2.remove(token)
  }

  override def get(token: AuthenticityToken): Option[Id] = {
    tokenMap2.get(token)
  }

  override def prolongTimeout(token: AuthenticityToken, timeoutInSeconds: Int): Unit = {
    //本当はtimeoutを延長するべき。
  }
}