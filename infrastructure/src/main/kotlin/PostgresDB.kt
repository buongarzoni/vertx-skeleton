import management.restaurant.infrastructure.RestaurantsTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object PostgresDB {
    private val username = System.getenv("DATABASE_USERNAME") ?: "chris"
    private val password = System.getenv("DATABASE_PASSWORD") ?: "1234"
    private val database = System.getenv("DATABASE_NAME") ?: "skeleton"
    private val host = System.getenv("DATABASE_HOST") ?: "localhost"
    private val port = System.getenv("DATABASE_PORT")?.toIntOrNull() ?: 5432

    fun initialize() {
        connect()
        createRestaurantsTable()
    }

    private fun connect() = Database.connect("jdbc:postgresql://$host:$port/$database",
        driver = "org.postgresql.Driver",
        user = username,
        password = password,
    )

    private fun createRestaurantsTable() = transaction { SchemaUtils.create(RestaurantsTable) }

}