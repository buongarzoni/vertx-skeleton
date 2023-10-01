package server

import io.vertx.ext.web.Router

interface Handler {
    suspend fun subscribe(router: Router)
}
