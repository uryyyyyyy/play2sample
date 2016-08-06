package com.github.uryyyyyyy.modules

import com.github.uryyyyyyy.daos.{MyDao, MyDaoImpl}
import com.github.uryyyyyyy.services.{MyService, MyServiceImpl}
import com.google.inject.AbstractModule

class ImplModule extends AbstractModule {

  override def configure() = {
    bind(classOf[MyService]).to(classOf[MyServiceImpl])
    bind(classOf[MyDao]).to(classOf[MyDaoImpl])
  }

}
