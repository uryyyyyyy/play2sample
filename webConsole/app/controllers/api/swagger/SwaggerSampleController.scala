package controllers.api.swagger

import javax.inject.{Inject, Singleton}

import akka.actor.ActorSystem
import io.swagger.annotations._
import play.api.mvc.{Action, Controller}

import scala.concurrent.ExecutionContext

@Singleton
@Api(value = "api sample 1")
class SwaggerSampleController @Inject() (actorSystem: ActorSystem) extends Controller {

  implicit val myExecutionContext: ExecutionContext = actorSystem.dispatcher

  @ApiOperation(
    httpMethod = "GET",
    nickname = "hello",
    value = ""
  )
  @ApiResponses(Array(
    new ApiResponse(code = 400, message = "Invalid ID supplied"),
    new ApiResponse(code = 404, message = "Pet not found")))
  def getPetById(
    @ApiParam(value = "ID of the pet to fetch") id: String) = Action {
    Ok(id)
  }
}
