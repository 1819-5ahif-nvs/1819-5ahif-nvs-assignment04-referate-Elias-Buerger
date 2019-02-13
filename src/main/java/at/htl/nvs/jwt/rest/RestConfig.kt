package at.htl.nvs.jwt.rest

import at.htl.nvs.jwt.rest.security.Role
import javax.annotation.security.DeclareRoles
import javax.ws.rs.ApplicationPath
import javax.ws.rs.core.Application

@ApplicationPath("/")
@DeclareRoles(Role.customer, Role.salesman)
class RestConfig : Application()