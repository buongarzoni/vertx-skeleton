package utils

import core.Code
import core.CoreException
import io.vertx.core.Future
import io.vertx.ext.web.RoutingContext
import io.vertx.kotlin.core.json.obj
import io.vertx.kotlin.core.json.json as jsonObject

fun RoutingContext.sendOk(): Future<Void> = response()
    .setStatusCode(Code.Ok.value)
    .send()

fun RoutingContext.sendCreated(): Future<Void> = response()
    .setStatusCode(Code.Created.value)
    .send()

fun RoutingContext.sendBadRequest(message: String): Future<Void> = response()
    .setStatusCode(Code.BadRequest.value)
    .end(jsonWithMessage(message))

fun RoutingContext.sendError(exception: CoreException): Future<Void> = response()
    .setStatusCode(exception.code)
    .end(jsonWithMessage(exception.message!!))

private fun jsonWithMessage(message: String) = jsonObject { obj(MESSAGE_FIELD to message) }.toString()
private const val MESSAGE_FIELD = "message"
