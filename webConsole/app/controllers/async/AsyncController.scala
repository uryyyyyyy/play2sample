package controllers.async

import javax.inject.{Inject, Singleton}

import akka.actor.ActorSystem
import org.joda.time.DateTime
import play.api.mvc.{Action, Controller, Result}

import scala.concurrent.duration._
import scala.concurrent._

@Singleton
class AsyncController @Inject() (actorSystem: ActorSystem, context: ExecutionContext) extends Controller {

  def myDispatcher = Action.async {
    implicit val myExecutionContext: ExecutionContext = actorSystem.dispatchers.lookup("myDispatcher")
    execution()
  }

  def myDispatcher2 = Action.async {
    implicit val myExecutionContext: ExecutionContext = actorSystem.dispatchers.lookup("myDispatcher2")
    execution()
  }

  def message2 = Action.async {
    implicit val myExecutionContext: ExecutionContext = actorSystem.dispatcher
    execution()
  }

  def message3 = Action.async {
    implicit val myExecutionContext: ExecutionContext = context
    execution()
  }

  def message4 = Action.async {
    import scala.concurrent.ExecutionContext.Implicits.global
    execution()
  }

  def message5 = Action.async {
    import play.api.libs.concurrent.Execution.Implicits._
    execution()
  }

  def execution()(implicit ec: ExecutionContext): Future[Result] ={
    val fList = (1 to 10).map(_ => Future{Thread.sleep(1000);println(Thread.currentThread.getName + ": " + DateTime.now)})
    Await.result(Future.sequence(fList), Duration.Inf)
    println("finish")
    Future{"hello"}.map(Ok(_))
  }

}
