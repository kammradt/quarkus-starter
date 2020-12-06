package org.kammradt.account.security

import org.eclipse.microprofile.jwt.Claims
import org.jose4j.jwt.JwtClaims
import org.kammradt.account.domain.Account
import org.kammradt.account.dto.TokenResponse
import java.util.*
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class TokenService {

    fun generate(account: Account): TokenResponse {
        return TokenResponse(TokenUtils().generateTokenString(buildJwtClaims(account)))
    }

    private fun buildJwtClaims(account: Account): JwtClaims {
        val jwtClaims = JwtClaims()
        jwtClaims.issuer = "ApplicationContext"
        jwtClaims.jwtId = UUID.randomUUID().toString()
        jwtClaims.subject = account.email
        jwtClaims.setClaim(Claims.upn.name, account.email)
        jwtClaims.setClaim(Claims.preferred_username.name, account.email)
        jwtClaims.setClaim(Claims.groups.name, listOf(account.role))
        jwtClaims.setAudience("using-jwt")
        jwtClaims.setExpirationTimeMinutesInTheFuture(720F)
        return jwtClaims
    }

}