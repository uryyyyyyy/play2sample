package controllers.api.auth

import javax.inject.{Inject, Singleton}

import akka.actor.ActorSystem
import com.github.uryyyyyyy.dto.AccountDTO
import jp.t2v.lab.play2.auth.LoginLogout
import play.api.libs.json.JsValue
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class AuthenticateController @Inject() (actorSystem: ActorSystem) extends Controller with LoginLogout with AuthConfigImpl {

  implicit val myExecutionContext: ExecutionContext = actorSystem.dispatcher

  private def authenticate(dto: AccountDTO): Either[Result, MyUser] = {
    AuthService.authenticate(dto.id, dto.password) match {
      case None => Left(Unauthorized("authentication failed"))
      case Some(user) => Right(user)
    }
  }

  private def jsonDecode(request: Request[JsValue]): Either[Result, AccountDTO] = {
    request.body.validate[AccountDTO].asOpt match {
      case None => Left(BadRequest("bad request"))
      case Some(v) => Right(v)
    }
  }

  def login() = Action.async(BodyParsers.parse.json) { implicit request =>

    val myUser = for{
      account <- jsonDecode(request).right
      myUser <- authenticate(account).right
    } yield myUser

    myUser match {
      case Left(r) => Future{r}
      case Right(user) => gotoLoginSucceeded(user.id)
    }
  }

  def logout() = Action.async { implicit request =>
    gotoLogoutSucceeded
  }
}