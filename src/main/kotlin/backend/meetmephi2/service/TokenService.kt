package backend.meetmephi2.service

import backend.meetmephi2.config.JwtProperties
import backend.meetmephi2.database.entity.User
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Service
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.userdetails.UserDetails
import java.util.Date

@Service
class TokenService(
    jwtProperties: JwtProperties
) {
    private val secretKey = Keys.hmacShaKeyFor(
        jwtProperties.key.toByteArray()
    )

    fun generate(
        userDetails: UserDetails,
        expirationDate: Date,
        additionalClaims: Map<String, Any> = emptyMap()
    ): String =
        Jwts.builder()
            .claims()
            .subject(userDetails.username)
            .issuedAt(Date())
            .expiration(expirationDate)
            .add(additionalClaims)
            .and()
            .signWith(secretKey)
            .compact()

    fun extractEmail(token: String) : String? =
        getAllClaims(token)
            .subject

    fun isExpired(token: String) : Boolean =
        getAllClaims(token)
            .expiration
            .before(Date())

    fun isValid(token: String, user : User?) : Boolean {
        val email = extractEmail(token)

        return user!!.email == email && !isExpired(token)
    }

    private fun getAllClaims(token : String): Claims {
        val parser = Jwts.parser()
            .verifyWith(secretKey)
            .build()

        return parser
            .parseSignedClaims(token)
            .payload
    }
}