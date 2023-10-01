package management.restaurant.domain

import management.zone.domain.ZoneId

fun restaurantRandom(
    restaurantId: RestaurantId = restaurantIdRandom(),
    name: Name = nameRandom(),
    zones: List<ZoneId>,
) = Restaurant
