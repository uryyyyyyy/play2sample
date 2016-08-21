package controllers.auth

import java.util.concurrent.TimeUnit

import akka.actor.ActorSystem
import akka.util.Timeout
import org.mockito.Mockito._
import org.scalatest.mock.MockitoSugar
import org.scalatest.{FunSpec, MustMatchers}
import play.api.cache.CacheApi
import play.api.test.{FakeRequest, Helpers}
import utils.{Administrator, AuthService, MyUser, NormalUser}

import scala.collection.mutable
import scala.concurrent.duration.Duration
import scala.reflect.ClassTag

class AuthorizeControllerTest extends FunSpec with MustMatchers with MockitoSugar  {

  describe("AuthorizeControllerTest") {

    implicit val timeout = Timeout(5000, TimeUnit.MILLISECONDS)

    it("returnUser"){

      val actorSystem = ActorSystem.apply()
      val controller = new AuthorizeController(actorSystem, null, null)
      val user = MyUser("admin", "pass", Administrator)
      val result = controller.returnUser(user)

      Helpers.contentAsString(result) mustBe "id: admin"
      Helpers.status(result) mustBe 200
    }

    it("checkAdminRole"){

      val memCache = new CacheApi(){
        val map = mutable.Map[String, Any]()
        override def set(key: String, value: Any, expiration: Duration): Unit = map.put(key, value)

        override def remove(key: String): Unit = map.remove(key)

        override def getOrElse[A: ClassTag](key: String, expiration: Duration = Duration.Inf)(orElse: => A): A = get(key).getOrElse(orElse)

        override def get[T: ClassTag](key: String): Option[T] = map.get(key).map(_.asInstanceOf[T])
      }

      val actorSystem = ActorSystem.apply()
      val mockService = mock[AuthService]
      when(mockService.authenticate("normal", "pass2")) thenReturn Some(MyUser("normal", "pass2", NormalUser))
      when(mockService.userOfId("normal")) thenReturn Some(MyUser("normal", "pass2", NormalUser))

      val authenticateController = new AuthenticateController(actorSystem, mockService, memCache)
      val response = authenticateController.login("normal", "pass2").apply(FakeRequest())
      val cookies = Helpers.cookies(response)

      val controller = new AuthorizeController(actorSystem, mockService, memCache)

      val result1 = controller.checkNormalRole().apply(FakeRequest().withCookies(cookies.toList :_*))
      Helpers.contentAsString(result1) mustBe "id: normal"
      Helpers.status(result1) mustBe 200

      val result2 = controller.checkAdminRole().apply(FakeRequest().withCookies(cookies.toList :_*))
      Helpers.contentAsString(result2) mustBe "No permission"
      Helpers.status(result2) mustBe 403
    }
  }
}