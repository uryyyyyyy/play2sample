package com.github.uryyyyyyy.services

import javax.inject.{Inject, Singleton}

import akka.actor.ActorSystem

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class MyServiceMock @Inject() (actorSystem: ActorSystem) extends MyService {

  implicit val myExecutionContext: ExecutionContext = actorSystem.dispatcher

  def exec(str: String): Future[String] = {
    Future{
      "mock " + str
    }
  }
}

