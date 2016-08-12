package controllers.auth

import javax.inject.Inject

import jp.t2v.lab.play2.auth.LoginLogout
import play.api.libs.ws.WSClient
import play.api.mvc.{Action, Controller}
import play.libs.Json.toJson
import utils.{AuthService, MyUser, NormalUser}

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

class GithubOauthController @Inject()(ws: WSClient) extends Controller with LoginLogout with AuthConfigImpl {

  def clientID = ""

  def clientSecret = ""

  def showSignIn = Action { implicit request =>
    Ok(views.html.githubSignin(clientID))
  }

  def callBackGitHub(code: String) = Action.async { implicit request =>

    println("start")
    import play.api.libs.json._
    val postBodyJson = Json.obj(
      "client_id" -> toJson(clientID),
      "client_secret" -> toJson(clientSecret),
      "code" -> toJson(code)
    )
    println("postBody=" + postBodyJson)

    val responce = ws.url("https://github.com/login/oauth/access_token")
      .withHeaders("Content-Type" ->  "application/json")
      .withHeaders("Accept" ->  "application/json")
      .post(postBodyJson.toString)
    val ss = Await.result(responce, Duration.Inf)

    println(ss.json)
    val accessToken = (ss.json \ "access_token").asOpt[String].get
    println(accessToken)

    val sss = ws.url("https://api.github.com/user")
      .withQueryString("access_token" ->  accessToken)
      .withHeaders("Accept" ->  "application/json")
      .get
    val user = Await.result(sss, Duration.Inf).json
    println(user)

    //WARNING: you should not use accessToken directly. it is just sample

    val id = (user \ "login").asOpt[String].get
    AuthService.create(MyUser(id, NormalUser))
    println(id)
    gotoLoginSucceeded(id)
  }

  def signOut = Action.async { implicit request =>
    gotoLogoutSucceeded
  }
}