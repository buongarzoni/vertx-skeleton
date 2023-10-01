package di.app.presentation

import di.management.presentation.Handlers
import server.ServerVerticle

object AppVerticles {
    val serverVerticle: ServerVerticle by lazy { ServerVerticle(handlers = listOf(Handlers.managementHandler)) }
}
