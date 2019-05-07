package io.vertx.example.web.graphql

import io.vertx.core.json.JsonObject
import io.vertx.core.{AsyncResult, Vertx => JVertx}
import io.vertx.ext.web.client.predicate.{ResponsePredicate => JResponsePredicate}
import io.vertx.ext.web.client.{HttpResponse => JHttpResponse, WebClient => JWebClient, WebClientOptions => JWebClientOptions}
import io.vertx.ext.web.codec.BodyCodec
import io.vertx.lang.scala.ScalaVerticle

class Client extends ScalaVerticle {
  override def start(): Unit = {
    val options = new JWebClientOptions().setDefaultPort(8080)
    val webClient = JWebClient.create(vertx.asJava.asInstanceOf[JVertx], options)
    val request = new JsonObject()
      .put("query", "query($secure: Boolean) { allLinks(secureOnly: $secure) { url, postedBy { name } } }")
      .put("variables", new JsonObject().put("secure", true))

    webClient.post("/graphql")
      .expect(JResponsePredicate.SC_OK)
      .expect(JResponsePredicate.JSON)
      .as(BodyCodec.jsonObject()).sendJsonObject(request, foo)
  }

  def foo(ar: AsyncResult[JHttpResponse[JsonObject]]): Unit  = {
    if (ar.succeeded) {
      val response = ar.result.body
      println("response = " + response.encodePrettily())
    }
    else ar.cause.printStackTrace()
  }
}
