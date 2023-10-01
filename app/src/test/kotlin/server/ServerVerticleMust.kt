package server

import PostgresDB
import io.vertx.core.Vertx
import io.vertx.ext.web.client.WebClient
import io.vertx.ext.web.client.WebClientOptions
import io.vertx.kotlin.coroutines.await
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import utils.isEqualTo
import kotlin.test.Test

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ServerVerticleMust {
    private val vertx = Vertx.vertx()
    private lateinit var client: WebClient

    @BeforeAll
    fun setup() {
        PostgresDB.initialize()
        deployServerVerticle()
        initializeClient()
    }

    @AfterAll
    fun tearDown() { vertx.close() }

    @Test
    fun `return ok`() {
        runBlocking {
            val response2 = executeRequest()

            response2.statusCode() isEqualTo OK_CODE
        }
    }

    private fun deployServerVerticle() = runBlocking { vertx.deployVerticle(ServerVerticle(emptyList())).await() }
    private fun initializeClient() {
        client = WebClient.create(vertx, WebClientOptions().setDefaultPort(8081).setDefaultHost("localhost"))
    }
    private suspend fun executeRequest() = client.get(REQUEST_URI).send().await()

    companion object {
        private const val REQUEST_URI = "/status"
        private const val OK_CODE = 200
    }
}
