package controllers

import java.io.File
import javax.inject.{Inject, Singleton}

import akka.actor.ActorSystem
import controllers.api.auth.{AuthConfigImpl, NormalUser}
import jp.t2v.lab.play2.auth.AuthElement
import play.api.Play
import play.api.mvc.{Action, Controller}

import scala.concurrent.{ExecutionContext, Future}


@Singleton
class HomeController @Inject() (actorSystem: ActorSystem) extends Controller with AuthElement with AuthConfigImpl{

  implicit val myExecutionContext: ExecutionContext = actorSystem.dispatcher

  def index = AsyncStack(AuthorityKey -> NormalUser) { implicit request =>
    Future {
      val f = new File("webConsole/front/index.html")
      Ok(scala.io.Source.fromFile(f.getCanonicalPath).mkString).as("text/html")
    }
  }

  def indexAll(path: String) = AsyncStack(AuthorityKey -> NormalUser) { implicit request =>
    Future {
      val f = new File("webConsole/front/index.html")
      Ok(scala.io.Source.fromFile(f.getCanonicalPath).mkString).as("text/html")
    }
  }

  def loginForm = Action.async {
    Future {
      val f = new File("webConsole/front/index.html")
      Ok(scala.io.Source.fromFile(f.getCanonicalPath).mkString).as("text/html")
    }
  }

  def dist(path: String) = Action {
    val f = new File("webConsole/front/dist/" + path)
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
