package controllers.auth

import javax.inject.{Inject, Singleton}

import akka.actor.ActorSystem
import jp.t2v.lab.play2.auth.LoginLogout
import play.api.mvc._
import utils.{AuthService, MyUser}

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class AuthenticateController @Inject() (actorSystem: ActorSystem) extends Controller with LoginLogout with AuthConfigImpl {

  implicit val myExecutionContext: ExecutionContext = actorSystem.dispatcher

  private def authenticate(userId: String, password: String): Either[Result, MyUser] = {
    AuthService.authenticate(userId, password) match {
      case None => Left(Unauthorized("authentication failed"))
      case Some(user) => Right(user)
    }
  }

  def login(userId: String, password: String) = Action.async { implicit request =>
    authenticate(userId, password) match {
      case Left(r) => Future{r}
      case Right(user) => gotoLoginSucceeded(user.id)
    }
  }

  def logout() = Action.async { implicit request =>
    gotoLogoutSucceeded
  }
}