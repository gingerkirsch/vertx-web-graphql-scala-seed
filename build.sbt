import sbt.Package._
import sbt._

version := "0.1"
scalaVersion := Version.Scala

lazy val playerApplication = (project in file("."))
  .settings(
    name := "vertx-web-graphql-scala-seed",
    mainClass in assembly := Some("io.vertx.core.Launcher"),
    libraryDependencies ++= Seq(
      Library.vertx_lang_scala,
      Library.vertx_web,
      Library.vertx_web_client,
      Library.vertx_web_graphql
    ),
    packageOptions += ManifestAttributes(
      ("Main-Verticle", "scala:tv.mycujoo.community.profile.MainVerticle"))
  )
