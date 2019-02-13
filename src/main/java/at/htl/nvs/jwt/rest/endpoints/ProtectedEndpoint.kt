package at.htl.nvs.jwt.rest.endpoints

import at.htl.nvs.jwt.rest.security.Role
import javax.annotation.security.RolesAllowed
import javax.json.Json
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/protected")
@RolesAllowed(Role.salesman, Role.customer)
open class ProtectedEndpoint {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/customer")
    @RolesAllowed(Role.customer)
    open fun customerResource(): Response {
        return Response.ok(Json.createObjectBuilder().add("Data", "Only customers can see this").build()).build()
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/salesman")
    @RolesAllowed(Role.salesman)
    open fun salesmanResource(): Response {
        return Response.ok(Json.createObjectBuilder().add("Data", "Only salesmen can see this").build()).build()
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    open fun resource(): Response {
        return Response.ok(Json.createObjectBuilder().add("Data", "Only salesmen and customers can see this").build()).build()
    }
}