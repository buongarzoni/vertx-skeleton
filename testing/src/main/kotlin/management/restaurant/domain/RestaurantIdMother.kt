package management.restaurant.domain

import utils.randomUUIDAsString

fun restaurantIdRandom() = RestaurantId.valueOf(randomUUIDAsString())!!
