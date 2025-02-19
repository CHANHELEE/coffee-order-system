package org.example.coffeeordersystem.controller

import org.example.coffeeordersystem.config.security.JwtTokenProvider
import org.example.coffeeordersystem.model.request.LoginRequest
import org.example.coffeeordersystem.model.response.LoginResponse
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val authenticationManager: AuthenticationManager,
    private val jwtTokenProvider: JwtTokenProvider
) {

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<LoginResponse> {

        val authToken = UsernamePasswordAuthenticationToken(loginRequest.username, loginRequest.password)
        val authentication = authenticationManager.authenticate(authToken)


        val role = authentication.authorities.firstOrNull()?.authority ?: "ROLE_USER"
        val token = jwtTokenProvider.createToken(authentication.name, role)

        return ResponseEntity.ok(LoginResponse(authentication.name, role, token))
    }

}