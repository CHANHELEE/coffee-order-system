package org.example.coffeeordersystem.service

import org.example.coffeeordersystem.model.dto.MenuDto
import org.example.coffeeordersystem.model.entity.Menu
import org.example.coffeeordersystem.model.mapper.MenuMapper
import org.example.coffeeordersystem.repository.MenuRepository
import org.springframework.stereotype.Service

@Service
class MenuService(
    private val menuRepository: MenuRepository,
    private val menuMapper: MenuMapper
) {

    fun findMenu(): List<MenuDto> {

        var menu: List<Menu> = menuRepository.findAll()
        val dto = menuMapper.toDto(menu)
        return dto
    }

    fun findMenu(id: Long): MenuDto {
//        TODO("RuntimeException  -> Custom Exception")
        var menu: Menu = menuRepository.findById(id).orElseThrow { RuntimeException("Menu not found") }
        return menuMapper.toDto(menu)
    }
}