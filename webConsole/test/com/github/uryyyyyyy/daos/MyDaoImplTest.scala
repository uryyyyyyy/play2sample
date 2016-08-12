package com.github.uryyyyyyy.daos

import org.scalatest.mock.MockitoSugar
import org.scalatest.{FunSpec, MustMatchers}
import play.api.db.Databases

class MyDaoImplTest extends FunSpec with MustMatchers with MockitoSugar {

  describe("MyServiceImplTest") {

    it("service"){
      val database = Databases(
        "org.h2.Driver",
        "jdbc:h2:mem:play"
      )

      val dao = new MyDaoImpl(database)
      val result = dao.exec()
      result mustBe Some("2")
    }
  }

}
