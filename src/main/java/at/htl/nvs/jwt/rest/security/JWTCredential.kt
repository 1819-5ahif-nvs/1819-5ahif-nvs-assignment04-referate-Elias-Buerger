package at.htl.nvs.jwt.rest.security

import javax.security.enterprise.credential.Credential

class JWTCredential(val value: String) : Credential