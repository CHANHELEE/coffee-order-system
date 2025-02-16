package org.example.coffeeordersystem.controller

import org.example.coffeeordersystem.model.dto.MenuDto
import org.example.coffeeordersystem.service.MenuService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/menu")
class MenuController @Autowired constructor(
    private val menuService: MenuService
) {

    @GetMapping("")
    fun findMenu(): ResponseEntity<List<MenuDto>> {

        val menu: List<MenuDto>? = menuService.findMenu()
        return ResponseEntity.ok(menu);
    }

    @GetMapping("/{id}")
    fun findMenu(@PathVariable id: Long): ResponseEntity<MenuDto> {

        val menu: MenuDto? = menuService.findMenu(id)
        return ResponseEntity.ok(menu);
    }
}