package controllers.stackable

import akka.actor.ActorSystem
import jp.t2v.lab.play2.stackc.{RequestAttributeKey, RequestWithAttributes, StackableController}
import play.api.mvc.{Controller, Result}

import scala.concurrent.Future
import scala.util.Random

trait MyStackLongElement extends StackableController {
  self: Controller =>

  val actorSystem: ActorSystem
  val random: Random

  private case object LongKey extends RequestAttributeKey[Long]

  override def proceed[A](req: RequestWithAttributes[A])(f: RequestWithAttributes[A] => Future[Result]): Future[Result] = {
    implicit val ctx = StackActionExecutionContext(req)
    val long = random.nextLong()
    val isFail = random.nextBoolean()
    if(isFail) return Future{ BadRequest }

    super.proceed(req.set(LongKey, long))(f)
  }

  def getStackedLong()(implicit req: RequestWithAttributes[_]): Long = req.get(LongKey).get

}