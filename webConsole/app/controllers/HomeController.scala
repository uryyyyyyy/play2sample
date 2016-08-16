package controllers

import javax.inject.{Inject, Singleton}

import akka.actor.ActorSystem
import play.api.Play
import play.api.mvc.{Action, AnyContent, Controller}

import scala.concurrent.{ExecutionContext, Future}


@Singleton
class HomeController @Inject()(actorSystem: ActorSystem) extends Controller {

  implicit val myExecutionContext: ExecutionContext = actorSystem.dispatcher

  def login = controllers.Assets.at(path="/public", file="login.html")

  def index = {
    loadIndex()
  }

  def indexAll(path: String) = {
    loadIndex()
  }

  private def loadIndex(): Action[AnyContent] = {
    controllers.Assets.at(path="/public", file="index.html")
  }

  //DIしたいけどRouterはController依存なので循環参照してしまう。
  def routes() = Action {
    val myRoutes = Play.current.routes.documentation map { r =>
      "%-10s %-50s %s".format(r._1, r._2, r._3)
    }
    Ok(myRoutes.mkString("\n"))
  }

  def apiAction() = Action{
    Ok("this is it!")
  }

}