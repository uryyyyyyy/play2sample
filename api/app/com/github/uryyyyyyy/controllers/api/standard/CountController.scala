package com.github.uryyyyyyy.controllers.api.standard

import javax.inject._

import akka.actor.ActorSystem
import com.github.uryyyyyyy.services.MyService
import play.api.mvc.{Action, Controller}

import scala.concurrent.ExecutionContext

@Singleton
class CountController @Inject() (myService: MyService, actorSystem: ActorSystem) extends Controller {

  implicit val myExecutionContext: ExecutionContext = actorSystem.dispatcher

  def message = Action.async {
    myService.exec("str").map { msg => Ok(msg) }
  }
}
