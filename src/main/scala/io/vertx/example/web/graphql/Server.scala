package io.vertx.example.web.graphql

import graphql.GraphQL
import graphql.schema.DataFetchingEnvironment
import graphql.schema.GraphQLSchema
import graphql.schema.idl.{RuntimeWiring, SchemaGenerator, SchemaParser, TypeDefinitionRegistry, TypeRuntimeWiring}
import io.vertx.core.Future
import io.vertx.core.{Vertx => JVertx}
import io.vertx.ext.web.{Router => JRouter}
import io.vertx.ext.web.handler.graphql.GraphQLHandler
import io.vertx.ext.web.handler.graphql.VertxDataFetcher
import java.util

import graphql.schema.idl.RuntimeWiring.newRuntimeWiring
import scala.collection.JavaConverters._
import io.vertx.lang.scala.ScalaVerticle

class Server extends ScalaVerticle {
  val peter: User = new User("Peter")
  val paul: User = new User("Paul")
  val jack: User = new User("Jack")
  val links = Link("https://vertx.io", "Vert.x project", peter) ::
    Link("https://www.eclipse.org", "Eclipse Foundation", paul) ::
    Link("http://reactivex.io", "ReactiveX libraries", jack) ::
    Link("https://www.graphql-java.com", "GraphQL Java implementation", peter) :: Nil

  override def start(): Unit = {
    prepareData()
    val router: JRouter = JRouter.router(vertx.asJava.asInstanceOf[JVertx])
    router.route("/graphql").handler(GraphQLHandler.create(createGraphQL))
    vertx.asJava.asInstanceOf[JVertx].createHttpServer.requestHandler(router).listen(8080)
  }

  private def prepareData(): Unit = {

  }

  private def createGraphQL: GraphQL = {
    val schema: String = vertx.fileSystem.readFileBlocking("links.graphqls").toString
    val schemaParser: SchemaParser = new SchemaParser
    val typeDefinitionRegistry: TypeDefinitionRegistry = schemaParser.parse(schema)
    val runtimeWiring: RuntimeWiring = newRuntimeWiring.`type`("Query", (builder: TypeRuntimeWiring.Builder) => {
      def foo(builder: TypeRuntimeWiring.Builder) = {
        val getAllLinks: VertxDataFetcher[util.List[Link]] = new VertxDataFetcher[util.List[Link]](this.getAllLinks)
        builder.dataFetcher("allLinks", getAllLinks)
      }

      foo(builder)
    }).build
    val schemaGenerator: SchemaGenerator = new SchemaGenerator
    val graphQLSchema: GraphQLSchema = schemaGenerator.makeExecutableSchema(typeDefinitionRegistry, runtimeWiring)
    GraphQL.newGraphQL(graphQLSchema).build
  }

  private def getAllLinks(env: DataFetchingEnvironment, future: Future[util.List[Link]]): Unit = {
    val secureOnly: Boolean = env.getArgument("secureOnly")
    val result: util.List[Link] = links.filter((link: Link) => !secureOnly || link.url.startsWith("https://")).asJava
    future.complete(result)
  }
}
