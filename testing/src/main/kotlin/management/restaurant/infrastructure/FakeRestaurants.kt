package management.restaurant.infrastructure

import management.restaurant.domain.Restaurant
import management.restaurant.domain.RestaurantId
import management.restaurant.domain.Restaurants

class FakeRestaurants: Restaurants {
    private val restaurants = mutableMapOf<RestaurantId, Restaurant>()

    override suspend fun get(restaurantId: RestaurantId) = restaurants[restaurantId]

    override suspend fun put(restaurant: Restaurant) {
        if (restaurants.containsKey(restaurant.id)) throw RuntimeException("Already exists")
        restaurants[restaurant.id] = restaurant
    }

}
