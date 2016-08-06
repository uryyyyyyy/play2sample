import sbt.Keys._

name := """play2Sample"""

lazy val commonSettings = Seq(
  version := "1.0",
  organization := "com.github.uryyyyyyy",
  scalaVersion := "2.11.8",
  libraryDependencies ++= Seq(
    jdbc,
    cache,
    ws,
    "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test
  )
)

lazy val api = (project in file("api"))
  .enablePlugins(PlayScala)
  .settings(commonSettings)
  .settings(Seq(
    name := "play2Sample-api",
    libraryDependencies ++= Seq(
      "mysql" % "mysql-connector-java" % "5.1.36"
    )
  ))

lazy val webConsole = (project in file("webConsole"))
  .enablePlugins(PlayScala)
  .settings(commonSettings)
  .settings(Seq(
    name := "play2Sample-main",
    libraryDependencies ++= Seq(
      "io.swagger" %% "swagger-play2" % "1.5.1",
      "jp.t2v" %% "stackable-controller" % "0.6.0",
      "jp.t2v" %% "play2-auth"        % "0.14.2",
      "jp.t2v" %% "play2-auth-social" % "0.14.2",
      "jp.t2v" %% "play2-auth-test"   % "0.14.2" % Test
    )
  ))
  .dependsOn(api)
  .aggregate(api)