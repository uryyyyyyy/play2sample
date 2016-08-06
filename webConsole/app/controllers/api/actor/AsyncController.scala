package controllers.api.actor

import javax.inject.{Inject, Singleton}

import akka.actor.ActorSystem
import org.joda.time.DateTime
import play.api.mvc.{Action, Controller}

import scala.concurrent.duration._
import scala.concurrent.{Await, ExecutionContext, Future, Promise}

@Singleton
class AsyncController @Inject() (actorSystem: ActorSystem, exec: ExecutionContext) extends Controller {

  def message = Action.async {
    implicit val myExecutionContext: ExecutionContext = actorSystem.dispatchers.lookup("myDispatcher")

    val fList = (1 to 10).map(_ => Future{Thread.sleep(1000);println(DateTime.now)})
    Await.result(Future.sequence(fList), Duration.Inf)
    println("finish")
    Future{"hallo"}.map { msg => Ok(msg) }
  }

  def message2 = Action.async {
    implicit val myExecutionContext: ExecutionContext = actorSystem.dispatcher

    val fList = (1 to 10).map(_ => Future{Thread.sleep(1000);println(DateTime.now)})
    Await.result(Future.sequence(fList), Duration.Inf)
    println("finish")
    Future{"hallo"}.map { msg => Ok(msg) }
  }

  def message4 = Action.async {
    implicit val myExecutionContext: ExecutionContext = exec

    val fList = (1 to 10).map(_ => Future{Thread.sleep(1000);println(DateTime.now)})
    Await.result(Future.sequence(fList), Duration.Inf)
    println("finish")
    Future{"hallo"}.map { msg => Ok(msg) }
  }

  def message3 = Action.async {
    implicit val myExecutionContext: ExecutionContext = actorSystem.dispatcher

    getFutureMessage(1.second).map { msg => Ok(msg) }
  }

  private def getFutureMessage(delayTime: FiniteDuration): Future[String] = {
    implicit val myExecutionContext: ExecutionContext = actorSystem.dispatcher

    val promise: Promise[String] = Promise[String]()
    actorSystem.scheduler.scheduleOnce(delayTime) { promise.success("Hi!") }
    promise.future
  }

}
