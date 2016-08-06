package com.github.uryyyyyyy.modules

import java.time.Clock

import com.github.uryyyyyyy.daos.{MyDao, MyDaoImpl}
import com.github.uryyyyyyy.modules.samples.ApplicationTimer
import com.github.uryyyyyyy.services.{MyService, MyServiceImpl}
import com.google.inject.AbstractModule

class ImplModule extends AbstractModule {

  override def configure() = {
    bind(classOf[MyService]).to(classOf[MyServiceImpl])
    bind(classOf[MyDao]).to(classOf[MyDaoImpl])

    bind(classOf[ApplicationTimer]).asEagerSingleton()
    bind(classOf[Clock]).toInstance(Clock.systemDefaultZone)
  }

}
