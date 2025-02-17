package org.example.coffeeordersystem.model.response

data class LoginResponse(
    val username: String,
    val role: String,
    val token: String
)
