package management.restaurant.domain

interface Restaurants {
    suspend fun get(restaurantId: RestaurantId): Restaurant?
    suspend fun put(restaurant: Restaurant)
}
