package utils

import com.google.inject.AbstractModule

class MyModule extends AbstractModule {

  override def configure() = {
    bind(classOf[AuthService]).asEagerSingleton()
  }

}