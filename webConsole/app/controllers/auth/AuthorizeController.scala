package controllers.auth

import javax.inject.{Inject, Singleton}

import akka.actor.ActorSystem
import jp.t2v.lab.play2.auth.AuthElement
import play.api.cache.CacheApi
import play.api.mvc.{Controller, Result}
import utils._

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class AuthorizeController @Inject() (actorSystem: ActorSystem,
  val authService: AuthService, val cache: CacheApi) extends Controller with AuthElement with AuthConfigImpl {

  implicit val myExecutionContext: ExecutionContext = actorSystem.dispatcher

  def checkAdminRole = AsyncStack(AuthorityKey -> Administrator) { implicit request =>
    val user = loggedIn
    returnUser(user)
  }

  def returnUser(user:MyUser): Future[Result] ={
    Future(Ok("id: " + user.id))
  }

  def checkNormalRole = AsyncStack(AuthorityKey -> NormalUser) { implicit request =>
    val user = loggedIn
    Future(Ok("id: " + user.id))
  }

}