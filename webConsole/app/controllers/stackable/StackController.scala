package controllers.stackable

import javax.inject.{Inject, Singleton}

import akka.actor.ActorSystem
import play.api.mvc.Controller

import scala.concurrent.{ExecutionContext, Future}
import scala.util.Random

@Singleton
class StackController @Inject() (
  override val actorSystem: ActorSystem,
  override val random:Random = new Random
) extends Controller with MyStackLongElement {

  implicit val myExecutionContext: ExecutionContext = actorSystem.dispatcher

  def message = AsyncStack { implicit req =>
    Future{
      val long = getStackedLong()
      Ok(long.toString)
    }
  }
}
