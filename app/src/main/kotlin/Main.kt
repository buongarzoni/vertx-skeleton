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
    //deployVerticle(MyServerVerticle(Handlers.managementHandler))
    deployVerticle(AppVerticles.serverVerticle)
    //deployVerticle(Verticles.managementVerticle)
}

class MyServerVerticle(
    private val managementHandler: ManagementHandler
): CoroutineVerticle() {
    override suspend fun start() {
        val router = router()
        managementHandler.subscribe(router)
        vertx
            .createHttpServer()
            .requestHandler(router)
            .listen(8081)
        println("CRB wee")
    }

    private fun router(): Router {
        val router = Router.router(vertx)
        router.route().handler(BodyHandler.create())
        router.get("/status").handler {
            it.response().setStatusCode(200).setStatusMessage("crb ok!").send()
        }
        return router
    }
}