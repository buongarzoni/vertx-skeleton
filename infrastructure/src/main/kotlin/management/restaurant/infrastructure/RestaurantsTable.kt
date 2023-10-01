package management.restaurant.infrastructure

import org.jetbrains.exposed.dao.IdTable

object RestaurantsTable: IdTable<String>() {
    override val id = varchar("id", 36).entityId()
    val name = varchar("name", 100)
}
