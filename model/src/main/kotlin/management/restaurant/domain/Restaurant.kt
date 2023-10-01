package management.restaurant.domain

import core.Entity
import management.zone.domain.ZoneId

class Restaurant(
    override val id: RestaurantId,
    val name: Name,
    val zones: List<ZoneId>,
): Entity() {
    companion object {
        fun new(
            id: RestaurantId,
            name: Name,
        ) = Restaurant(
            id = id,
            name = name,
            zones = emptyList(),
        )
    }
}
