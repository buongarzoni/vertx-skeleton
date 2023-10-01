package management

import core.CoreException
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import io.vertx.kotlin.core.json.get
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import management.restaurant.actions.CreateRestaurant
import management.restaurant.domain.Name
import management.restaurant.domain.Restaurant
import management.restaurant.domain.RestaurantId
import server.Handler
import utils.sendBadRequest
import utils.sendCreated
import utils.sendError

class ManagementHandler(
    private val createRestaurant: CreateRestaurant,
): Handler {
    override suspend fun subscribe(router: Router) {
        router.post("/restaurant/create/:restaurant_id").handler { ctx ->
            val restaurantIdParam = ctx.getRestaurantId()
            val nameParam = ctx.getRestaurantName() ?: run {
                ctx.sendBadRequest("Body needs name param")
                return@handler
            }
            val restaurantId = RestaurantId.valueOf(restaurantIdParam) ?: run {
                ctx.sendBadRequest("'$restaurantIdParam' is an invalid restaurant id")
                return@handler
            }
            val name = Name.valueOf(nameParam) ?: run {
                ctx.sendBadRequest("'$nameParam' is an invalid restaurant name")
                return@handler
            }

            ctx.executeAction(restaurantId, name)
        }
    }

    private fun RoutingContext.executeAction(restaurantId: RestaurantId, name: Name) = CoroutineScope(Dispatchers.IO).launch {
        try {
            createRestaurant.execute(Restaurant.new(restaurantId, name))
            sendCreated()
        } catch (exception: CoreException) {
            sendError(exception)
        }
    }

    private fun RoutingContext.getRestaurantId(): String = pathParam(RESTAURANT_ID_PARAM)

    private fun RoutingContext.getRestaurantName(): String? = body().asJsonObject()?.get(RESTAURANT_NAME_PARAM)

    companion object {
        private const val RESTAURANT_ID_PARAM = "restaurant_id"
        private const val RESTAURANT_NAME_PARAM = "name"
    }
}

