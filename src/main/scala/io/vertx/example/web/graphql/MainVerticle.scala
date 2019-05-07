package io.vertx.example.web.graphql

import io.vertx.scala.core.Vertx
import io.vertx.lang.scala.ScalaVerticle

object MainVerticle extends App {
  val vertx = Vertx.vertx()
  vertx.deployVerticleFuture(ScalaVerticle.nameForVerticle[Server])
  vertx.deployVerticleFuture(ScalaVerticle.nameForVerticle[Client])
}
