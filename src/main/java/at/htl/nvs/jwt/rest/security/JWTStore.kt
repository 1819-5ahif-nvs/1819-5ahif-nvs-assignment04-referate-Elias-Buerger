package at.htl.nvs.jwt.rest.security

import at.htl.nvs.jwt.entities.User
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import javax.enterprise.context.RequestScoped
import javax.security.enterprise.credential.Credential
import javax.security.enterprise.identitystore.IdentityStore
import javax.security.enterprise.identitystore.CredentialValidationResult

@RequestScoped
open class JWTStore : IdentityStore {

    private val privateKey = "123very hidden key that no one will ever guess123"

    override fun validate(credential: Credential): CredentialValidationResult {
        try {
            if (credential is JWTCredential) {
                val claims = Jwts.parser()
                        .setSigningKey(privateKey)
                        .parseClaimsJws(credential.value)
                return CredentialValidationResult(claims.body["name"] as String, mutableSetOf(claims.body["scope"] as String))
            }
        } catch (ex: Exception) {
            return CredentialValidationResult.NOT_VALIDATED_RESULT
        }
        return CredentialValidationResult.INVALID_RESULT
    }

    open fun generateJWT(user: User): JWTCredential =
            JWTCredential(Jwts.builder()
                    .claim("name", user.name)
                    .claim("scope", Role.of(user))
                    .signWith(SignatureAlgorithm.HS256, privateKey)
                    .compact())
}