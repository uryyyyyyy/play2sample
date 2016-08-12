package utils

import scala.collection.mutable

object AuthService {
  private val users:mutable.Map[String, MyUser] = mutable.Map()

  def userOfId(userId: String): Option[MyUser] = {
    //本当はDBに繋ぐ
    users.get(userId)
  }

  def create(user: MyUser): Unit = {
    //本当はDBに繋ぐ
    if(users.get(user.id).isEmpty){
      users.put(user.id, user)
      println(users)
    }
  }
}