package org.example.coffeeordersystem.service

import org.example.coffeeordersystem.model.dto.MenuDto
import org.springframework.stereotype.Service

@Service
class MenuService {

    fun findMenu(): List<MenuDto> {
        TODO("menu Repository call")
        var menuDto : List<MenuDto> = listOf<MenuDto>()
        return menuDto
    }

    fun findMenu(id: Long): MenuDto {
        TODO("menu Repository call")
        var menuDto : MenuDto = MenuDto(id = 1, name = "Americano", description = "Hot Americano", price = 3000L)
        return menuDto
    }
}