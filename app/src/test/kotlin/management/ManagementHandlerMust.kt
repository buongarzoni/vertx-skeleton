package management

import di.management.presentation.Handlers
import io.vertx.core.Vertx
import io.vertx.core.json.JsonObject
import io.vertx.ext.web.client.WebClient
import io.vertx.ext.web.client.WebClientOptions
import io.vertx.kotlin.core.json.get
import io.vertx.kotlin.core.json.json
import io.vertx.kotlin.core.json.obj
import io.vertx.kotlin.coroutines.await
import kotlinx.coroutines.runBlocking
import management.restaurant.domain.RestaurantId
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import server.ServerVerticle
import utils.bodyMessage
import utils.isEqualTo
import utils.randomString
import java.util.UUID
import kotlin.test.Test

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ManagementHandlerMust {
    private val vertx = Vertx.vertx()
    private lateinit var client: WebClient
    private val restaurantId = UUID.randomUUID().toString()
    private val restaurantName = randomString(intRange = 1..10)

    @BeforeAll
    fun setup() {
        PostgresDB.initialize()
        initServerWithManagementHandler()
        initializeClient()
    }

    @AfterAll
    fun tearDown() { vertx.close() }

    @Test
    fun `return conflict error when try to create restaurant with same id`() {
        runBlocking {
            val response1 = executeRequest()
            val response2 = executeRequest()

            response1.statusCode() isEqualTo RESOURCE_CREATED_CODE
            response2.statusCode() isEqualTo CONFLICT_CODE
            response2.bodyMessage isEqualTo "Already exists"
        }
    }

    @Test
    fun `return bad request when no name is send in body`() {
        runBlocking {
            val response = executeRequest(body = json { obj() })

            response.statusCode() isEqualTo BAD_REQUEST
            response.bodyMessage isEqualTo "Body needs name param"
        }
    }

    @Test
    fun `return bad request when name is invalid`() {
        val longName = randomString(intRange = 1..20)
        runBlocking {
            val response = executeRequest(body = jsonBody(longName))

            response.statusCode() isEqualTo BAD_REQUEST
            response.bodyMessage isEqualTo "'$longName' is an invalid restaurant name"
        }
    }

    @Test
    fun `return bad request when id is invalid`() {
        val invalidUUID = randomString()
        runBlocking {
            val response = executeRequest(restaurantId = invalidUUID)

            response.statusCode() isEqualTo BAD_REQUEST
            response.bodyMessage isEqualTo "'$invalidUUID' is an invalid restaurant id"
        }
    }

    private fun initServerWithManagementHandler() = runBlocking { vertx.deployVerticle(ServerVerticle(listOf(Handlers.managementHandler))).await() }
    private fun initializeClient() {
        client = WebClient.create(vertx, WebClientOptions().setDefaultPort(8081).setDefaultHost("localhost"))
    }
    private suspend fun executeRequest(
        restaurantId: String = this.restaurantId,
        body: JsonObject = jsonBody(),
    ) = client
        .post(REQUEST_URI + restaurantId)
        .sendJson(body)
        .await()

    private fun jsonBody(restaurantName: String = this.restaurantName) = json { obj(NAME_PARAM to restaurantName) }

    companion object {
        private const val REQUEST_URI = "/restaurant/create/"
        private const val NAME_PARAM = "name"
        private const val RESOURCE_CREATED_CODE = 201
        private const val BAD_REQUEST = 400
        private const val CONFLICT_CODE = 409
    }
}
