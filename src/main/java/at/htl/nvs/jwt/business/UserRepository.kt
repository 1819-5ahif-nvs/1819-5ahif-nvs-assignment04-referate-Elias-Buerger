package at.htl.nvs.jwt.business

import at.htl.nvs.jwt.entities.Customer
import at.htl.nvs.jwt.entities.Salesman
import at.htl.nvs.jwt.entities.User
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
open class UserRepository {

    open fun find(name: String): User? = users.find { it.name == name }

    companion object {
        private val users = arrayOf(
                Customer("Max Mustermann", "12345", "Maxi IV"),
                Salesman("John Smith", "hello", "Limesstraße 12-14")
        )
    }
}