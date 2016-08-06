package filters

import javax.inject._

import com.github.uryyyyyyy.utils.Loggable
import play.api._
import play.api.http.HttpFilters

@Singleton
class MyFilters @Inject() (env: Environment, loggingFilter: LoggingFilter) extends HttpFilters with Loggable{

  override val filters = {
    logger.info(env.mode.toString)

    Seq(loggingFilter)
  }
}
