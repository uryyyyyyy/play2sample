package controllers.swagger

import play.api.libs.json.Json

case class UserDTO(id: Long, name: String)

object UserDTO {
  implicit val writes = Json.writes[UserDTO]
  implicit val reads = Json.reads[UserDTO]
}

case class UserWithTimeStampDTO(user: UserDTO, unixTime: Long)

object UserWithTimeStampDTO {
  implicit val writes = Json.writes[UserWithTimeStampDTO]
  implicit val reads = Json.reads[UserWithTimeStampDTO]
}

case class MessageDTO(id: String, messages: List[String])

object MessageDTO {
  implicit val writes = Json.writes[MessageDTO]
  implicit val reads = Json.reads[MessageDTO]
}