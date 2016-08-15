package controllers.api.actor

import javax.inject.{Inject, Singleton}

import akka.actor.ActorSystem
import play.api.mvc.{Action, Controller}

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class AsyncController @Inject() (actorSystem: ActorSystem) extends Controller {

  def message = Action.async {
    implicit val myExecutionContext: ExecutionContext = actorSystem.dispatcher
    Future{"hello"}.map { msg => Ok(msg) }
  }

}
