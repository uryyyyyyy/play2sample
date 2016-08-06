package controllers.api.stackable

import jp.t2v.lab.play2.stackc.{RequestAttributeKey, RequestWithAttributes, StackableController}
import play.api.mvc.{Controller, Result}

import scala.concurrent.Future
import scala.util.Random

trait MyStackStringElement extends StackableController {
  self: Controller =>

  private case object StringKey extends RequestAttributeKey[String]

  override def proceed[A](req: RequestWithAttributes[A])(f: RequestWithAttributes[A] => Future[Result]): Future[Result] = {
    implicit val ctx = StackActionExecutionContext(req)
    val str = new Random().nextString(10)
    val isFail = new Random().nextBoolean()
    if(isFail) return Future{ BadRequest }

    super.proceed(req.set(StringKey, str))(f)
  }

  def getStack()(implicit req: RequestWithAttributes[_]): String = req.get(StringKey).get

}