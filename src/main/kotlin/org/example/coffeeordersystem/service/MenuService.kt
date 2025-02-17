package org.example.coffeeordersystem.service

import org.example.coffeeordersystem.model.response.MenuResponse
import org.example.coffeeordersystem.model.entity.Menu
import org.example.coffeeordersystem.model.mapper.MenuMapper
import org.example.coffeeordersystem.repository.MenuRepository
import org.springframework.stereotype.Service

@Service
class MenuService(
    private val menuRepository: MenuRepository,
    private val menuMapper: MenuMapper
) {

    fun findMenu(): List<MenuResponse> {

        var menu: List<Menu> = menuRepository.findAll()
        return menuMapper.toResponse(menu)
    }

    fun findMenu(id: Long): MenuResponse {
//        TODO("RuntimeException  -> Custom Exception")
        var menu: Menu = menuRepository.findById(id).orElseThrow { RuntimeException("Menu not found") }
        return menuMapper.toResponse(menu)
    }
}