package org.example.coffeeordersystem.model.response

data class MenuResponse(
    var id: Long,
    var name: String,
    var description: String? = null,
    var price: Long
)
