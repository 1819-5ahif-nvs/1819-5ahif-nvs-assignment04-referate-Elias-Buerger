package at.htl.nvs.jwt.rest.security

import at.htl.nvs.jwt.entities.Salesman
import at.htl.nvs.jwt.entities.User

class Role {
    companion object {
        const val customer = "customer"
        const val salesman = "salesman"

        fun of(user: User) =
                if (user is Salesman) salesman
                else customer
    }
}