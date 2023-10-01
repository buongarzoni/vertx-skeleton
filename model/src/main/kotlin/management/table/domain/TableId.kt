package management.table.domain

import core.Id
import java.util.*

@JvmInline
value class TableId private constructor(
    val value: String
): Id {
    companion object {

        fun valueOf(string: String) = try {
            val uuid = UUID.fromString(string)
            TableId(uuid.toString())
        } catch (illegalArgumentException: IllegalArgumentException) {
            null
        }

    }
}
