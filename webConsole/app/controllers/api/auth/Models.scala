package controllers.api.auth

case class MyUser(
  id: String,
  pass: String,
  role: MyRole)


sealed trait MyRole
case object Administrator extends MyRole
case object NormalUser extends MyRole