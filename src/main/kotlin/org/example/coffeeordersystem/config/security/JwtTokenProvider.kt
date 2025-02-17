package org.example.coffeeordersystem.config.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtTokenProvider {

    private val secretKey = "your-very-secret-key-your-very-secret-key"
    private val validityInMilliseconds: Long = 360000000 // 100시간

    fun createToken(username: String, role: String): String {
        val claims: Claims = Jwts.claims().setSubject(username)
        claims["roles"] = role

        val now = Date()
        val validity = Date(now.time + validityInMilliseconds)
        val key = Keys.hmacShaKeyFor(secretKey.toByteArray())

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(validity)
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()
    }

    fun validateToken(token: String): Boolean {
        return try {
            val claimsJws = Jwts.parserBuilder()
                .setSigningKey(secretKey.toByteArray())
                .build()
                .parseClaimsJws(token)
            !claimsJws.body.expiration.before(Date())
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
        //TODO(Exception 세분화 및 e.printStackTrace()제거)
    }

    fun getAuthentication(token: String): Authentication {
        val claimsJws = Jwts.parserBuilder()
            .setSigningKey(secretKey.toByteArray())
            .build()
            .parseClaimsJws(token)
        val claims = claimsJws.body

        val username = claims.subject
        val userDetails = User(username, "", emptyList())
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }
}