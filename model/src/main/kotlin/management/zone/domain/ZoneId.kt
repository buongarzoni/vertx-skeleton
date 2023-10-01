package management.zone.domain

import core.Id
import java.util.*

@JvmInline
value class ZoneId private constructor(
    val value: String
): Id {
    companion object {

        fun valueOf(string: String) = try {
            val uuid = UUID.fromString(string)
            ZoneId(uuid.toString())
        } catch (illegalArgumentException: IllegalArgumentException) {
            null
        }

    }
}