package controllers.di

import java.util.concurrent.TimeUnit

import akka.actor.ActorSystem
import akka.util.Timeout
import com.github.uryyyyyyy.services.MyService
import org.mockito.Matchers.any
import org.mockito.Mockito._
import org.scalatest.mock.MockitoSugar
import org.scalatest.{FunSpec, MustMatchers}
import play.api.mvc.Result
import play.api.test.{FakeRequest, Helpers}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class MyDIControllerTest extends FunSpec with MustMatchers with MockitoSugar  {

  describe("MyDIControllerTest") {

    implicit val timeout = Timeout(5000, TimeUnit.MILLISECONDS)

    it("controllerTest"){
      val mockService = mock[MyService]
      when(mockService.exec(any[String])) thenReturn Future{"str"}

      val actorSystem = ActorSystem.apply()
      val controller = new MyDIController(mockService, actorSystem)

      val result: Future[Result] = controller.message().apply(FakeRequest())
      Helpers.contentAsString(result) mustBe "str"
      Helpers.status(result) mustBe 200
    }
  }

}