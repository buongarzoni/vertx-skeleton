package di.management.actions

import di.management.infrastructure.Repositories
import management.restaurant.actions.CreateRestaurant

object Actions {
    val createRestaurant: CreateRestaurant by lazy { CreateRestaurant(restaurants = Repositories.restaurants) }
}
