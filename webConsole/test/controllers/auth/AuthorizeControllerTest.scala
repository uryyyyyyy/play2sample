package controllers.auth

import java.util.concurrent.TimeUnit

import akka.actor.ActorSystem
import akka.util.Timeout
import org.scalatest.mock.MockitoSugar
import org.scalatest.{FunSpec, MustMatchers}
import play.api.test.Helpers
import utils.{Administrator, MyUser}

class AuthorizeControllerTest extends FunSpec with MustMatchers with MockitoSugar  {

  describe("AuthorizeControllerTest") {

    implicit val timeout = Timeout(5000, TimeUnit.MILLISECONDS)

    it("AuthorizeController"){

      val actorSystem = ActorSystem.apply()
      val controller = new AuthorizeController(actorSystem, null)
      val user = MyUser("admin", "pass", Administrator)
      val result = controller.returnUser(user)

      Helpers.contentAsString(result) mustBe "id: admin"
      Helpers.status(result) mustBe 200
    }
  }
}