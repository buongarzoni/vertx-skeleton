import di.app.presentation.AppVerticles
import io.vertx.core.Vertx

fun main() {
    val vertx = Vertx.vertx()

    vertx.deployVerticles()
    PostgresDB.initialize()
}

private fun Vertx.deployVerticles() {
    deployVerticle(AppVerticles.serverVerticle)
}
