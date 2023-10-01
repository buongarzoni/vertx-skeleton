package management.restaurant.infrastructure

import core.Code
import core.CoreError
import core.CoreException
import management.restaurant.domain.Restaurant
import management.restaurant.domain.RestaurantId
import management.restaurant.domain.Restaurants
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

class PostgresRestaurants: Restaurants {
    override suspend fun get(restaurantId: RestaurantId): Restaurant? {
        val resultRow = restaurantId.getRestaurantRow() ?: return null
        return resultRow.toRestaurant()
    }

    override suspend fun put(restaurant: Restaurant) {
        if (restaurant.exists()) throw CoreException(CoreError(Code.Conflict,"Already exists"))
        insert(restaurant)
    }

    private fun Restaurant.exists() = id.getRestaurantRow() != null

    private fun RestaurantId.getRestaurantRow() = transaction {
        RestaurantsTable.select {
            RestaurantsTable.id.eq(value)
        }.firstOrNull()
    }

    private fun insert(restaurant: Restaurant) {
        transaction {
            RestaurantsTable.insert {
                val entityId = EntityID(restaurant.id.value, this)
                it[id] = entityId
                it[name] = restaurant.name.value
            }
        }
    }

    private fun ResultRow.toRestaurant(): Restaurant? {
        val dto = RestaurantDTO(
            id = get(RestaurantsTable.id).value,
            name = get(RestaurantsTable.name),
            zoneIds = emptyList(),
        )
        return dto.toModel()
    }
}
