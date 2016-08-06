package com.github.uryyyyyyy.modules

import com.github.uryyyyyyy.daos.{MyDao, MyDaoImpl}
import com.github.uryyyyyyy.services.{MyService, MyServiceImpl, MyServiceMock}
import com.google.inject.AbstractModule

class MockModule extends AbstractModule {

  override def configure() = {
    bind(classOf[MyService]).to(classOf[MyServiceMock])
  }

}
