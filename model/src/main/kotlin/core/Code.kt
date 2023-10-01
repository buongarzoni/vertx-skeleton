package core

sealed class Code(val value: Int) {
    data object Ok: Code(200)
    data object Created: Code(201)
    data object BadRequest: Code(400)
    data object Conflict: Code(409)
}
