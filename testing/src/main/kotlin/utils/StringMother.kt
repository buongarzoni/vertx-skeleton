package utils

fun randomString(
    stringType: StringType = StringType.Alphanumeric,
    intRange: IntRange = 1 .. 10,
) = intRange.map { stringType.characters.random() }.joinToString("")

sealed interface StringType {
    data object Alphabetic : StringType {
        override val characters = ('a'..'z') + ('A'..'Z')
    }

    data object Numeric : StringType {
        override val characters = ('0'..'9').toList()
    }

    data object Alphanumeric : StringType {
        override val characters = Alphabetic.characters + Numeric.characters
    }

    val characters: List<Char>
}
