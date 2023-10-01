package utils

import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

infix fun <T> T.isEqualTo(other: T) = assertEquals(other, this)
infix fun <T> T.isDifferentThan(other: T) = assertNotEquals(other, this)
fun <T> T.isNull() = assertNull(this)
fun <T> T.isNotNull() = assertNotNull(this)
