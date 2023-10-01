package server

import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.BodyHandler
import io.vertx.kotlin.coroutines.CoroutineVerticle
import utils.sendOk

class ServerVerticle(
    private val handlers: List<Handler>,
): CoroutineVerticle() {
    override suspend fun start() {
        val router = router()
        handlers.forEach { it.subscribe(router) }
        vertx
            .createHttpServer()
            .requestHandler(router)
            .listen(8081)
    }

    private fun router(): Router {
        val router = Router.router(vertx)
        router.route().handler(BodyHandler.create())
        router.get("/status").handler { it.sendOk() }
        return router
    }
}
