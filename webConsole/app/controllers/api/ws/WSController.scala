package controllers.api.ws

import javax.inject.{Inject, Singleton}

import akka.actor.ActorSystem
import play.api.libs.ws.WSClient
import play.api.mvc.{Action, Controller}

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._

@Singleton
class WSController @Inject() (wsClient: WSClient, actorSystem: ActorSystem) extends Controller {

  implicit val myExecutionContext: ExecutionContext = actorSystem.dispatcher

  def message = Action.async {
    val request = wsClient.url("http://qiita.com/api/v2/items")
        .withHeaders("Accept" -> "application/json")
        .withRequestTimeout(10000.millis)
        .withQueryString("per_page" -> "1")

    request.get()
      .map(response => response.json)
      .map(json => Ok(json))
  }
}
