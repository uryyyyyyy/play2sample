package controllers.auth

import javax.inject.{Inject, Singleton}

import akka.actor.ActorSystem
import jp.t2v.lab.play2.auth.AuthElement
import play.api.mvc.Controller

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class AuthorizeController @Inject() (actorSystem: ActorSystem) extends Controller with AuthElement with AuthConfigImpl {

  implicit val myExecutionContext: ExecutionContext = actorSystem.dispatcher

  def checkAuthority = AsyncStack(AuthorityKey -> Administrator) { implicit request =>
    val user = loggedIn
    Future{ Ok("id: " + user.id)}
  }

}
