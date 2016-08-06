package com.github.uryyyyyyy.services

import akka.actor.ActorSystem
import com.github.uryyyyyyy.daos.MyDao
import org.scalatest.{FunSpec, MustMatchers}

import scala.concurrent.Await
import scala.concurrent.duration.Duration

class MyServiceImplTest extends FunSpec with MustMatchers  {

  describe("MyServiceImplTest") {

    it("service"){
      val myDao = new MyDao(){
        override def exec(str: String): String = str
      }
      val actorSystem = ActorSystem.apply()
      val service = new MyServiceImpl(myDao, actorSystem)

      val result = Await.result(service.exec("str"), Duration.Inf)
      "str" mustBe result
    }
  }

}
