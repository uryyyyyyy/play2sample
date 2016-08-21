package controllers.auth

import jp.t2v.lab.play2.auth._
import org.keyczar.Signer
import play.api.mvc.{RequestHeader, Result, Results}
import utils._

import scala.concurrent.{ExecutionContext, Future}
import scala.reflect.{ClassTag, classTag}

trait AuthConfigImpl extends AuthConfig {

  val authService: AuthService

  override val idContainer: AsyncIdContainer[Id]

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
    Future.successful(authService.userOfId(id))
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
    Future.successful(
      (user.role, authority) match {
        case (Administrator, _)       => true // AdminならどんなActionでも全権限を開放
        case (NormalUser, NormalUser) => true // ユーザがNormalUserで、ActionがNormalUserなら権限あり。もしActionがAdminだけなら権限なしになる。
        case _                        => false
      }
    )
  }

  /**
    * セッショントークンの管理をします。デフォルトではPlayのCache APIを用いています。
    */
//  override lazy val idContainer: AsyncIdContainer[Id] = AsyncIdContainer(new utils.CacheIdContainer[Id](cache))

  /**
    * tokenのやりとりをどのように行うかを定義します。
    * デフォルトではCookieによるトークンの授受が実装されています。
    */
  override lazy val tokenAccessor: TokenAccessor = new CookieTokenAccessor(cookieMaxAge = Some(sessionTimeoutInSeconds)){

    val signer = new Signer("/home/shiba/Desktop/keys")

    override def verifyHmac(token: SignedToken): Option[AuthenticityToken] = {
      val (hmac, value) = token.splitAt(34)//HMAC_SHA1
      if (safeEquals(signer.sign(value), hmac)) Some(value) else None
    }

    override def sign(token: AuthenticityToken): SignedToken = signer.sign(token) + token
  }
}