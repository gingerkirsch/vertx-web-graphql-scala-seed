= Vert.x Web GraphQL Scala example

Based on https://github.com/gingerkirsch/vertx-examples/tree/master/web-graphql-examples[Java Example]

Here you will find examples demonstrating Vert.x Web GraphQL in action.
Currently Vert.x does not provide rewritten part in Scala as for other components of Vert.x, yet we can still use it in Scala in "javified" way

https://vertx.io/docs/vertx-web-graphql/java/[Vert.x Web GraphQL] extends Vert.x Web with the https://www.graphql-java.com/[GraphQL-Java] library so that you can build a GraphQL server.

== Simple GraphQL server and client

This example uses the Vert.x Web client to send a request to the GraphQL server and log the response.

The link:src/main/resources/links.graphqls[GraphQL schema] describes the data with:

* `Link` and `User` types
* the `allLinks` query

Run MainVerticle in order to deploy both Server and Client

* link:src/main/java/io/vertx/example/web/graphql/MainVerticle.scala[Main Verticle]
