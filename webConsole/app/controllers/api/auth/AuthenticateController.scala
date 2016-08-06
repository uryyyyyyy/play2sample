package controllers.api.auth

import javax.inject.{Inject, Singleton}

import akka.actor.ActorSystem
import jp.t2v.lab.play2.auth.LoginLogout
import play.api.mvc.{Action, Controller}

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class AuthenticateController @Inject() (actorSystem: ActorSystem) extends Controller with LoginLogout with AuthConfigImpl {

  implicit val myExecutionContext: ExecutionContext = actorSystem.dispatcher

  def login(userId: String, password: String) = Action.async { implicit request =>
    AuthService.authenticate(userId, password) match {
      case None => Future.successful(Unauthorized("authentication failed"))
      case Some(user) => gotoLoginSucceeded(user.id)
    }
  }

}
