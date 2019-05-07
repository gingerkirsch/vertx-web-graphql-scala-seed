import sbt._

object Version {
  final val Scala           = "2.12.7"
  final val Vertx           = "3.7.0"
}

object Library {
  val vertx_lang_scala                  = "io.vertx" %% "vertx-lang-scala"                        % Version.Vertx
  val vertx_web                         = "io.vertx" %% "vertx-web-scala"                         % Version.Vertx
  val vertx_web_client                  = "io.vertx" %% "vertx-web-client-scala"                  % Version.Vertx
  val vertx_web_graphql                 = "io.vertx" % "vertx-web-graphql"                        % Version.Vertx
}
