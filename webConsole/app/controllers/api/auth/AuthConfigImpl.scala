package controllers.api.auth

import com.github.uryyyyyyy.utils.Loggable
import jp.t2v.lab.play2.auth._
import play.api.mvc.Results.Forbidden
import play.api.mvc.{RequestHeader, Result, Results}

import scala.collection.mutable
import scala.concurrent.{ExecutionContext, Future}
import scala.reflect.{ClassTag, classTag}

trait AuthConfigImpl extends AuthConfig with Loggable{

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
    Future.successful(Results.Redirect("/"))
  }

  override def logoutSucceeded(request: RequestHeader)(implicit context: ExecutionContext): Future[Result] = {
    Future.successful(Results.Redirect("/login?logout=true"))
  }

  override def authenticationFailed(request: RequestHeader)(implicit context: ExecutionContext): Future[Result] = {
    if(request.acceptedTypes.map(_.toString()).contains("text/html")){
      Future.successful(Results.Redirect("/login?failed=true"))
    }else{
      Future.successful(Results.Unauthorized("Unauthorized"))
    }
  }

  override def authorizationFailed(request: RequestHeader, user: User, authority: Option[Authority])(implicit context: ExecutionContext): Future[Result] = {
    Future.successful(Results.Forbidden("No permission"))
  }

  override def authorize(user: User, authority: Authority)(implicit context: ExecutionContext): Future[Boolean] = Future.successful {
    (user.role, authority) match {
      case (Administrator, _)       => true // AdminならどんなActionでも全権限を開放
      case (NormalUser, NormalUser) => true // ユーザがNormalUserで、ActionがNormalUserなら権限あり。もしActionがAdminだけなら権限なしになる。
      case _                        => false
    }
  }

  override lazy val idContainer: AsyncIdContainer[Id] = AsyncIdContainer(new SessionHandler[Id](CacheImpl.tokenMap, CacheImpl.tokenMap2))
}

object CacheImpl{
  val tokenMap: mutable.Map[String, AuthenticityToken] = mutable.Map()
  val tokenMap2: mutable.Map[AuthenticityToken, String] = mutable.Map()
}