package org.example.coffeeordersystem.model.mapper

import org.example.coffeeordersystem.model.response.MenuResponse
import org.example.coffeeordersystem.model.entity.Menu
import org.mapstruct.Mapper


@Mapper(componentModel = "spring")
interface MenuMapper {

    fun toResponse(menu: Menu): MenuResponse

    fun toResponse(menu: List<Menu>): List<MenuResponse>
}