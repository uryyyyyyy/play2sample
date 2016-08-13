package controllers.auth

import jp.t2v.lab.play2.auth._
import play.api.mvc.{RequestHeader, Result, Results}
import utils._

import scala.concurrent.{ExecutionContext, Future}
import scala.reflect.{ClassTag, classTag}

trait AuthConfigImpl extends AuthConfig {

  override type Id = String

  override type User = MyUser

  /**
    * 認可のために使う権限を表現した型。
    */
  override type Authority = MyRole

  /**
    * A `ClassTag` is used to retrieve an id from the Cache API.
    * Use something like this:
    */
  override val idTag: ClassTag[Id] = classTag[Id]

  override def sessionTimeoutInSeconds: Int = 3600 // 1時間

  override def resolveUser(id: Id)(implicit context: ExecutionContext): Future[Option[User]] = {
    Future.successful(AuthService.userOfId(id))
  }

  override def loginSucceeded(request: RequestHeader)(implicit context: ExecutionContext): Future[Result] = {
    Future.successful(Results.Ok("login success"))
  }

  override def logoutSucceeded(request: RequestHeader)(implicit context: ExecutionContext): Future[Result] = {
    Future.successful(Results.Ok("logout success"))
  }

  override def authenticationFailed(request: RequestHeader)(implicit context: ExecutionContext): Future[Result] = {
    Future.successful(Results.Unauthorized("Unauthorized"))
  }

  override def authorizationFailed(request: RequestHeader, user: User, authority: Option[Authority])(implicit context: ExecutionContext): Future[Result] = {
    Future.successful(Results.Forbidden("No permission"))
  }

  override def authorize(user: User, authority: Authority)(implicit context: ExecutionContext): Future[Boolean] = {
    //Authorizationの時に書く
    Future.successful(true)
  }

  /**
    * セッショントークンの管理をします。デフォルトではPlayのCache APIを用いています。
    */
  override lazy val idContainer: AsyncIdContainer[Id] = AsyncIdContainer(new CacheIdContainer[Id])

  /**
    * tokenのやりとりをどのように行うかを定義します。
    * デフォルトではCookieによるトークンの授受が実装されています。
    */
  override lazy val tokenAccessor: TokenAccessor = new CookieTokenAccessor(
    cookieMaxAge = Some(sessionTimeoutInSeconds)
  )
}