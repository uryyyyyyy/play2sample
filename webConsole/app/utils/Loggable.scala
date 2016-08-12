package utils

import org.slf4j.LoggerFactory

trait Loggable {
  protected lazy val logger = LoggerFactory.getLogger(this.getClass)
}
