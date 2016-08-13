package controllers.swagger

import javax.inject.{Inject, Singleton}

import io.swagger.annotations._
import play.api.libs.json.{JsError, Json}
import play.api.mvc.{Action, BodyParsers, Controller}

@Singleton
@Api(value = "userAPI")
class UserController @Inject() () extends Controller {

  @ApiOperation(
    produces = "application/json",
    consumes = "application/json",
    httpMethod = "GET",
    value = "fetch user by id",
    response = classOf[UserDTO]
  )
  @ApiResponses(Array(
    new ApiResponse(code = 400, message = "Invalid ID", response = classOf[MessageDTO]),
    new ApiResponse(code = 404, message = "target user not found", response = classOf[MessageDTO]))
  )
  def getById(
    @ApiParam(value = "id used by fetch target user") id: Long
  ) = Action {
    val user = UserDTO(id, "uryyyyyyy")
    Ok(Json.toJson(user))
  }

  @ApiOperation(
    produces = "application/json",
    consumes = "application/json",
    httpMethod = "GET",
    value = "fetch all users",
    response = classOf[Array[UserDTO]]
  )
  def getAll() = Action {
    val users = Array(UserDTO(1, "uryyyyyyy"), UserDTO(2, "uryyyyyyy2"))
    Ok(Json.toJson(users))
  }

  @ApiOperation(
    produces = "application/json",
    consumes = "application/json",
    httpMethod = "POST",
    value = "save user",
    response = classOf[MessageDTO]
  )
  @ApiImplicitParams(Array(
    new ApiImplicitParam(name = "user", value = "User with timestamp", required = true, dataType = "controllers.swagger.UserWithTimeStampDTO", paramType = "body")
  ))
  @ApiResponses(Array(
    new ApiResponse(code = 400, message = "bad query", response = classOf[MessageDTO])
  ))
  def edit() = Action(BodyParsers.parse.json) { request =>
    request.body.validate[UserWithTimeStampDTO].asEither match {
      case Left(errors) => BadRequest(Json.obj("status" ->"BAD", "message" -> JsError.toJson(errors)))
      case Right(user) => {
        //save method
        val msg = MessageDTO("id1", List(s"user '${user.user.id}' saved."))
        Ok(Json.toJson(msg))
      }
    }
  }

}