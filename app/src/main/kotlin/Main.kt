import di.app.presentation.AppVerticles
import io.vertx.core.Vertx
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.BodyHandler
import io.vertx.kotlin.coroutines.CoroutineVerticle
import management.ManagementHandler

fun main() {
    val vertx = Vertx.vertx()

    vertx.deployVerticles()
    PostgresDB.initialize()
}

private fun Vertx.deployVerticles() {
    deployVerticle(AppVerticles.serverVerticle)
}
