package controllers.api.stackable

import javax.inject.{Inject, Singleton}

import akka.actor.ActorSystem
import play.api.mvc.Controller

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class StackController @Inject() (actorSystem: ActorSystem) extends Controller with MyStackStringElement {

  implicit val myExecutionContext: ExecutionContext = actorSystem.dispatcher

  def message = AsyncStack { implicit req =>
    val str = getStack()
    Future{str}.map(str => Ok(str))
  }
}
