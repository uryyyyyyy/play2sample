name := """play2Sample"""

scalaVersion := "2.11.8"

resolvers ++= Seq(
  "Sonatype OSS Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
  "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
)

lazy val commonSettings = Seq(
  version := "1.0",
  organization := "com.github.uryyyyyyy",
  scalaVersion := "2.11.8"
)

lazy val webConsole = (project in file("webConsole"))
  .enablePlugins(PlayScala)
  .dependsOn(swagger)
  .settings(commonSettings)
  .settings(Seq(
    name := "play2Sample-main",
    libraryDependencies ++= Seq(
      jdbc,
      cache,
      ws,
      "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test
    )
  ))

lazy val swagger = RootProject(uri("ssh://git@github.com/CreditCardsCom/swagger-play.git"))