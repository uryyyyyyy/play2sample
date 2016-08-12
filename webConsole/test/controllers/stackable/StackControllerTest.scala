package controllers.stackable

import java.util.concurrent.TimeUnit

import akka.actor.ActorSystem
import akka.util.Timeout
import org.mockito.Mockito._
import org.scalatest.mock.MockitoSugar
import org.scalatest.{FunSpec, MustMatchers}
import play.api.mvc.Result
import play.api.test.{FakeRequest, Helpers}

import scala.concurrent.Future
import scala.util.Random

class StackControllerTest extends FunSpec with MustMatchers with MockitoSugar  {

  describe("StackControllerTest") {

    implicit val timeout = Timeout(5000, TimeUnit.MILLISECONDS)

    it("StackController success"){
      val randomMock = mock[Random]
      when(randomMock.nextLong()) thenReturn 100L
      when(randomMock.nextBoolean()) thenReturn false
      val actorSystem = ActorSystem.apply()
      val controller = new StackController(actorSystem, randomMock)

      val result: Future[Result] = controller.message().apply(FakeRequest())
      Helpers.contentAsString(result) mustBe "100"
      Helpers.status(result) mustBe 200
    }

    it("StackController fail"){
      val randomMock = mock[Random]
      when(randomMock.nextLong()) thenReturn 100L
      when(randomMock.nextBoolean()) thenReturn true
      val actorSystem = ActorSystem.apply()
      val controller = new StackController(actorSystem, randomMock)

      val result: Future[Result] = controller.message().apply(FakeRequest())
      Helpers.status(result) mustBe 400
      Helpers.contentAsString(result) mustBe ""
    }
  }
}