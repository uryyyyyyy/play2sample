name := """play2Sample"""

scalaVersion := "2.11.8"

resolvers ++= Seq(
  "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
)

lazy val commonSettings = Seq(
  version := "1.0",
  organization := "com.github.uryyyyyyy",
  scalaVersion := "2.11.8"
)

lazy val webConsole = (project in file("webConsole"))
  .enablePlugins(PlayScala)
  .settings(commonSettings)
  .settings(Seq(
    name := "play2Sample-main",
    libraryDependencies ++= Seq(
      ws,
      cache,
      "jp.t2v" %% "play2-auth"        % "0.14.2",
      "jp.t2v" %% "play2-auth-social" % "0.14.2",
      "jp.t2v" %% "play2-auth-test"   % "0.14.2" % "test",
      "jp.t2v" %% "stackable-controller" % "0.6.0",
      "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test,
      "org.mockito" % "mockito-all" % "1.10.19" % Test
    )
  ))