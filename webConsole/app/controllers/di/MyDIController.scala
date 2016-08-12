package controllers.di

import javax.inject.{Inject, Singleton}

import akka.actor.ActorSystem
import com.github.uryyyyyyy.services.MyService
import play.api.mvc.{Action, Controller}

import scala.concurrent.ExecutionContext

@Singleton
class MyDIController @Inject() (myService: MyService, actorSystem: ActorSystem) extends Controller {

  implicit val myExecutionContext: ExecutionContext = actorSystem.dispatcher

  def message = Action.async {
    myService.exec("str").map { msg => Ok(msg) }
  }
}