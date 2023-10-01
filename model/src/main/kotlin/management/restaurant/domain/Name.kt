package management.restaurant.domain

@JvmInline
value class Name private constructor(
    val value: String,
) {
    companion object {
        fun valueOf(string: String) = if(string.isValid()) Name(string) else null

        private fun String.isValid() = isNotEmpty() && isNotBlank() && length < 20
    }
}
