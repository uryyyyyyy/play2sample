package com.github.uryyyyyyy.dto

import play.api.libs.json.Json

case class AccountDTO(id:String, password: String)

object AccountDTO {
  //these implicit serve convert(Json <-> ScalaObject)
  implicit val writes = Json.writes[AccountDTO]
  implicit val reads = Json.reads[AccountDTO]
}