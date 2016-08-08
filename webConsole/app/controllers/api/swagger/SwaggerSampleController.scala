package controllers.api.swagger

import javax.inject.{Inject, Singleton}

import akka.actor.ActorSystem
import io.swagger.annotations.{ApiParam, ApiResponse, ApiResponses}
import play.api.libs.ws.WSClient
import play.api.mvc.{Action, Controller}

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._

@Singleton
class SwaggerSampleController @Inject() (actorSystem: ActorSystem) extends Controller {

  implicit val myExecutionContext: ExecutionContext = actorSystem.dispatcher

  @ApiResponses(Array(
    new ApiResponse(code = 400, message = "Invalid ID supplied"),
    new ApiResponse(code = 404, message = "Pet not found")))
  def getPetById(
    @ApiParam(value = "ID of the pet to fetch") id: String) = Action {
    Ok(id)
  }
}
