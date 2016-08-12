package controllers

import java.io.File
import javax.inject.{Inject, Singleton}

import akka.actor.ActorSystem
import controllers.api.auth.{AuthConfigImpl, NormalUser}
import jp.t2v.lab.play2.auth.AuthElement
import play.api.Play
import play.api.mvc.{Action, Controller, Result}

import scala.concurrent.{ExecutionContext, Future}


@Singleton
class HomeController @Inject() (actorSystem: ActorSystem) extends Controller with AuthElement with AuthConfigImpl{

  implicit val myExecutionContext: ExecutionContext = actorSystem.dispatcher

  def index = AsyncStack(AuthorityKey -> NormalUser) { implicit request =>
    Future {loadIndex()}
  }

  def indexAll(path: String) = AsyncStack(AuthorityKey -> NormalUser) { implicit request =>
    Future {loadIndex()}
  }

  def loginForm = Action.async {
    Future {loadIndex()}
  }

  def loadIndex():Result = {
    val f = new File("webConsole/front/index.html")
    Ok(scala.io.Source.fromFile(f.getCanonicalPath).mkString).as("text/html")
  }

  def dist(path: String) = Action {
    val f = new File("webConsole/front/dist/" + path)
    Ok(scala.io.Source.fromFile(f.getCanonicalPath).mkString).as("text/html")
  }

  def favicon() = Action {
    val f = new File("webConsole/front/public/favicon")
    Ok(scala.io.Source.fromFile(f.getCanonicalPath).mkString).as("text/html")
  }

  //DIしたいけどRouterはController依存なので循環参照してしまう。
  def routes() = Action {
    val myRoutes = Play.current.routes.documentation map { r =>
      "%-10s %-50s %s".format(r._1, r._2, r._3)
    }
    Ok(myRoutes.mkString("\n"))
  }

}
