package management.restaurant.actions

import management.restaurant.domain.Restaurant
import management.restaurant.domain.Restaurants

class CreateRestaurant(
    private val restaurants: Restaurants,
) {
    suspend fun execute(restaurant: Restaurant) = restaurants.put(restaurant)
}
