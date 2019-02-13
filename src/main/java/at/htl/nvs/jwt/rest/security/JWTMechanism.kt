package at.htl.nvs.jwt.rest.security

import javax.enterprise.context.ApplicationScoped
import javax.security.enterprise.authentication.mechanism.http.HttpMessageContext
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpServletRequest
import javax.security.enterprise.AuthenticationStatus
import javax.inject.Inject
import javax.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism
import javax.ws.rs.core.HttpHeaders

@ApplicationScoped
open class JWTMechanism : HttpAuthenticationMechanism {

    @Inject
    private lateinit var store: JWTStore

    override fun validateRequest(req: HttpServletRequest, res: HttpServletResponse, msg: HttpMessageContext): AuthenticationStatus {
        val authHeader = req.getHeader(HttpHeaders.AUTHORIZATION) ?: return AuthenticationStatus.NOT_DONE
        val token = authHeader.split(" ")[1]
        val jwt = JWTCredential(token)
        return msg.notifyContainerAboutLogin(store.validate(jwt))
    }
}