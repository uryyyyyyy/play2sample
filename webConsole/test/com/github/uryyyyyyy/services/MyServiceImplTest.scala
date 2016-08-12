package com.github.uryyyyyyy.services

import akka.actor.ActorSystem
import com.github.uryyyyyyy.daos.MyDao
import org.mockito.Matchers.any
import org.mockito.Mockito._
import org.scalatest.mock.MockitoSugar
import org.scalatest.{FunSpec, MustMatchers}

import scala.concurrent.Await
import scala.concurrent.duration.Duration

class MyServiceImplTest extends FunSpec with MustMatchers with MockitoSugar {

  describe("MyServiceImplTest") {

    it("service"){
      val mockDao = mock[MyDao]
      when(mockDao.exec()) thenReturn Some("mm")

      val actorSystem = ActorSystem.apply()
      val service = new MyServiceImpl(mockDao, actorSystem)
      val result = Await.result(service.exec("aaa"), Duration.Inf)
      result mustBe "aaa mm"
    }
  }

}
