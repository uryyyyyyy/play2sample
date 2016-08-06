package modules

import com.github.uryyyyyyy.services.{MyService, MyServiceMock}
import com.google.inject.AbstractModule

class MockModule extends AbstractModule {

  override def configure() = {
    bind(classOf[MyService]).to(classOf[MyServiceMock])
  }

}
