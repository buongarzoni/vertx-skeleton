package management.restaurant.infrastructure

import management.restaurant.domain.Name
import management.restaurant.domain.Restaurant
import management.restaurant.domain.RestaurantId

data class RestaurantDTO(
    val id: String,
    val name: String,
    val zoneIds: List<String>,
) {
    fun toModel(): Restaurant? {
        val restaurantId = RestaurantId.valueOf(id) ?: return null
        val name = Name.valueOf(name) ?: return null
        return Restaurant(
            id = restaurantId,
            name = name,
            zones = emptyList(),
        )
    }
}
