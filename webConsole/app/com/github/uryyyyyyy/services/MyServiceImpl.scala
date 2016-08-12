package com.github.uryyyyyyy.services

import javax.inject.{Inject, Singleton}

import akka.actor.ActorSystem
import com.github.uryyyyyyy.daos.MyDao

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class MyServiceImpl @Inject() (myDao: MyDao, actorSystem: ActorSystem) extends MyService {

  implicit val myExecutionContext: ExecutionContext = actorSystem.dispatcher

  def exec(str: String): Future[String] = {
    Future{
      str + " " + myDao.exec().getOrElse("null")
    }
  }
}

