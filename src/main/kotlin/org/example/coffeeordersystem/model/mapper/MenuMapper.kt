package org.example.coffeeordersystem.model.mapper

import org.example.coffeeordersystem.model.dto.MenuDto
import org.example.coffeeordersystem.model.entity.Menu
import org.mapstruct.Mapper


@Mapper(componentModel = "spring")
interface MenuMapper {

    fun toDto(menu: Menu): MenuDto

    fun toDto(menu: List<Menu>): List<MenuDto>
}