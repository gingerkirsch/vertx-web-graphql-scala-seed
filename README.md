= Vert.x Web GraphQL Scala example

Based on [Java Example](https://github.com/vert-x3/vertx-examples/tree/master/web-graphql-examples)

Here you will find examples demonstrating Vert.x Web GraphQL in action.
Currently Vert.x does not provide rewritten part in Scala as for other components of Vert.x, yet we can still use it in Scala in "javified" way

[Vert.x Web GraphQL](https://vertx.io/docs/vertx-web-graphql/java/) extends Vert.x Web with the [GraphQL-Java](https://www.graphql-java.com/) library so that you can build a GraphQL server.

== Simple GraphQL server and client

This example uses the Vert.x Web client to send a request to the GraphQL server and log the response.

The [GraphQL schema](link:src/main/resources/links.graphqls) describes the data with:

* `Link` and `User` types
* the `allLinks` query

Run [Main Verticle](link:src/main/java/io/vertx/example/web/graphql/MainVerticle.scala) in order to deploy both Server and Client
