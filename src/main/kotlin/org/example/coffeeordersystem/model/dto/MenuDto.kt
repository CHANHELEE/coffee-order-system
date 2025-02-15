package org.example.coffeeordersystem.model.dto

data class MenuDto(
    var id: Long,
    var name: String,
    var description: String? = null,
    var price: Long
)
