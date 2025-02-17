package org.example.coffeeordersystem.controller

import org.example.coffeeordersystem.model.response.MenuResponse
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
    fun findMenu(): ResponseEntity<List<MenuResponse>> {

        val menu: List<MenuResponse>? = menuService.findMenu()
        return ResponseEntity.ok(menu);
    }

    @GetMapping("/{id}")
    fun findMenu(@PathVariable id: Long): ResponseEntity<MenuResponse> {

        val menu: MenuResponse? = menuService.findMenu(id)
        return ResponseEntity.ok(menu);
    }
}