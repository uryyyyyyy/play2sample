package controllers.api.standard

import java.util.concurrent.TimeUnit

import akka.actor.ActorSystem
import akka.util.Timeout
import com.github.uryyyyyyy.services.MyService
import org.scalatest.{FunSpec, MustMatchers}
import play.api.mvc.Result
import play.api.test.FakeRequest
import play.api.test.Helpers

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class MyDIControllerTest extends FunSpec with MustMatchers  {

  describe("MyDIControllerTest") {

    implicit val timeout = Timeout(5000, TimeUnit.MILLISECONDS)

    it("controllerTest"){
      val myService = new MyService(){
        override def exec(str: String): Future[String] = Future{ "str"}
      }
      val actorSystem = ActorSystem.apply()
      val controller = new MyDIController(myService, actorSystem)

      val result: Future[Result] = controller.message().apply(FakeRequest())
      Helpers.contentAsString(result) mustBe "str"
      Helpers.status(result) mustBe 200
    }
  }

}