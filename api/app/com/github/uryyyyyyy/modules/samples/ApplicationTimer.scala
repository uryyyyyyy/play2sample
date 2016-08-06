package com.github.uryyyyyyy.modules.samples

import java.time.{Clock, Instant}
import java.util.concurrent.TimeUnit
import javax.inject.{Inject, Singleton}

import akka.actor.ActorSystem
import com.github.uryyyyyyy.utils.Loggable
import play.api.inject.ApplicationLifecycle

import scala.concurrent.{ExecutionContext, Future}
import scala.concurrent.duration._

@Singleton
class ApplicationTimer @Inject() (clock: Clock, appLifecycle: ApplicationLifecycle, actorSystem: ActorSystem) extends Loggable {

  implicit val myExecutionContext: ExecutionContext = actorSystem.dispatcher

  private val start: Instant = clock.instant
  logger.info(s"ApplicationTimer demo: Starting application at $start.")

  actorSystem.scheduler.schedule(Duration.Zero, Duration.create(10, TimeUnit.SECONDS)) {
    logger.info(clock.instant.toString)
  }

  // When the application starts, register a stop hook with the
  // ApplicationLifecycle object. The code inside the stop hook will
  // be run when the application stops.
  appLifecycle.addStopHook { () =>
    val stop: Instant = clock.instant
    val runningTime: Long = stop.getEpochSecond - start.getEpochSecond
    logger.info(s"ApplicationTimer demo: Stopping application at ${clock.instant} after ${runningTime}s.")
    Future.successful(())
  }
}
