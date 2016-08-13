package utils

object AuthService {
  private val users = List[(MyUser, String)](
    (MyUser("admin", Administrator), "pass1"),
    (MyUser("normal", NormalUser), "pass2")
  )

  def userOfId(userId: String): Option[MyUser] = {
    //本当はDBに繋ぐ
    users.find(_._1.id == userId).map(_._1)
  }

  def authenticate(userId: String, password: String): Option[MyUser] = {
    //本当はDBに繋ぐ
    users.find(u => u._1.id == userId && u._2 == password).map(_._1)
  }
}