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
  .settings(commonSettings)
  .settings(Seq(
    name := "play2Sample-main",
    libraryDependencies ++= Seq(
      "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test
    )
  ))