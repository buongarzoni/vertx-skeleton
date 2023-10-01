package management.restaurant.domain

import utils.randomString

fun nameRandom() = Name.valueOf(randomString(intRange = 1 .. 20))!!
