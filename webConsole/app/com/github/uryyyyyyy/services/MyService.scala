package com.github.uryyyyyyy.services

import scala.concurrent.Future

trait MyService {
  def exec(str: String): Future[String]
}
