package utils

object AuthService {
  private val users = List[MyUser](
    MyUser("admin", "pass1", Administrator),
    MyUser("normal", "pass2", NormalUser)
  )

  def userOfId(userId: String): Option[MyUser] = {
    //本当はDBに繋ぐ
    users.find(_.id == userId)
  }

  def authenticate(userId: String, password: String): Option[MyUser] = {
    //本当はDBに繋ぐ
    users.find(u => u.id == userId && u.pass == password)
  }
}