package controllers.api.auth

import javax.inject.{Inject, Singleton}

import akka.actor.ActorSystem
import org.joda.time.DateTime
import play.api.mvc.{Action, Controller}

import scala.concurrent.duration._
import scala.concurrent.{Await, ExecutionContext, Future, Promise}

@Singleton
class AuthController @Inject() (actorSystem: ActorSystem) extends Controller {

  def message = Action.async {
    implicit val myExecutionContext: ExecutionContext = actorSystem.dispatchers.lookup("myDispatcher")

    val fList = (1 to 10).map(_ => Future{Thread.sleep(1000);println(DateTime.now)})
    Await.result(Future.sequence(fList), Duration.Inf)
    println("finish")
    Future{"hallo"}.map { msg => Ok(msg) }
  }

}
