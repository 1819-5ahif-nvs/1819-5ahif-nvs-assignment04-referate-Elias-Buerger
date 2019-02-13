package at.htl.nvs.jwt.rest.endpoints

import at.htl.nvs.jwt.business.UserRepository
import at.htl.nvs.jwt.rest.security.JWTStore
import javax.annotation.security.PermitAll
import javax.inject.Inject
import javax.json.JsonObject
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.core.HttpHeaders
import javax.ws.rs.core.Response

@Path("login")
open class LoginEndpoint {

    @Inject
    private lateinit var userRepository: UserRepository

    @Inject
    private lateinit var jwtStore: JWTStore

    @POST
    open fun login(credentials: JsonObject): Response {
        val user = userRepository.find(credentials.getString("username"))
        if(user != null && user.password == credentials.getString("password")) {
            val jwt = jwtStore.generateJWT(user)
            return Response.ok().header(HttpHeaders.AUTHORIZATION, "BEARER " + jwt.value).build()
        }
        return Response.status(400).build()
    }
}