package org.kammradt.account.security

import org.eclipse.microprofile.jwt.Claims
import org.jose4j.jws.AlgorithmIdentifiers
import org.jose4j.jws.JsonWebSignature
import org.jose4j.jwt.JwtClaims
import org.jose4j.jwt.NumericDate
import java.nio.charset.StandardCharsets
import java.security.KeyFactory
import java.security.PrivateKey
import java.security.spec.PKCS8EncodedKeySpec
import java.util.*

class TokenUtils {

    @Throws(Exception::class)
    fun generateTokenString(claims: JwtClaims): String {
        val currentTimeInSecs = currentTimeInSecs()
        claims.issuedAt = NumericDate.fromSeconds(currentTimeInSecs)
        claims.setClaim(Claims.auth_time.name, NumericDate.fromSeconds(currentTimeInSecs))
        val jws = JsonWebSignature()
        jws.payload = claims.toJson()
        jws.key = readPrivateKey()
        jws.keyIdHeaderValue = "privateKey.pem"
        jws.setHeader("typ", "JWT")
        jws.algorithmHeaderValue = AlgorithmIdentifiers.RSA_USING_SHA256
        return jws.compactSerialization
    }

    @Throws(Exception::class)
    private fun readPrivateKey(): PrivateKey {
        val contentIS = TokenUtils::class.java.getResourceAsStream("/privateKey.pem")
        val tmp = ByteArray(4096)
        val length = contentIS.read(tmp)
        return decodePrivateKey(String(tmp, 0, length, StandardCharsets.UTF_8))
    }

    @Throws(Exception::class)
    private fun decodePrivateKey(pemEncoded: String): PrivateKey {
        val encodedBytes = toEncodedBytes(pemEncoded)
        val keySpec = PKCS8EncodedKeySpec(encodedBytes)
        val kf = KeyFactory.getInstance("RSA")
        return kf.generatePrivate(keySpec)
    }

    private fun toEncodedBytes(pemEncoded: String): ByteArray {
        val normalizedPem = removeBeginEnd(pemEncoded)
        return Base64.getDecoder().decode(normalizedPem)
    }

    private fun removeBeginEnd(pem: String): String {
        var normalizedPem = pem
        normalizedPem = normalizedPem.replace("-----BEGIN (.*)-----".toRegex(), "")
        normalizedPem = normalizedPem.replace("-----END (.*)----".toRegex(), "")
        normalizedPem = normalizedPem.replace("\r\n".toRegex(), "")
        normalizedPem = normalizedPem.replace("\n".toRegex(), "")
        return normalizedPem.trim { it <= ' ' }
    }

    private fun currentTimeInSecs(): Long {
        val currentTimeMS = System.currentTimeMillis()
        return (currentTimeMS / 1000)
    }
}