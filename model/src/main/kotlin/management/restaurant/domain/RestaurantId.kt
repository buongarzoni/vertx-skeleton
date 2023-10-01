package management.restaurant.domain

import core.Id
import java.util.*

@JvmInline
value class RestaurantId private constructor(
    val value: String,
): Id {

    companion object {

        fun valueOf(string: String) = try {
            val uuid = UUID.fromString(string)
            RestaurantId(uuid.toString())
        } catch (illegalArgumentException: IllegalArgumentException) {
            null
        }

    }
}
