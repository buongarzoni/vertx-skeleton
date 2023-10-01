package di.management.infrastructure

import management.restaurant.infrastructure.PostgresRestaurants

object Repositories {
    val restaurants: PostgresRestaurants by lazy { PostgresRestaurants() }
}
