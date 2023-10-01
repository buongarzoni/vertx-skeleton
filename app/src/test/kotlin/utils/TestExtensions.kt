package utils

import io.vertx.core.buffer.Buffer
import io.vertx.ext.web.client.HttpResponse
import io.vertx.kotlin.core.json.get

val HttpResponse<Buffer>.bodyMessage get() = body().toJsonObject().get<String>("message")
