package utils

case class MyUser(
  id: String,
  role: MyRole)


sealed trait MyRole
case object Administrator extends MyRole
case object NormalUser extends MyRole