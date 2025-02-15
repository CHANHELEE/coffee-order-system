package org.example.coffeeordersystem.controller

import org.example.coffeeordersystem.ControllerTestSupporter
import org.example.coffeeordersystem.model.dto.MenuDto
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest(MenuController::class)
class MenuControllerTest: ControllerTestSupporter() {

    @DisplayName("[커피 메뉴 목록 조회 API] 전체 메뉴를 조회한다.")
    @Test
    @Throws(Exception::class)
    fun testFindMenu() {

        // given
        val menuResponses: List<MenuDto> = listOf(
            MenuDto(id = 1, name = "Americano", description = "Hot Americano", price = 3000L),
            MenuDto(id = 2, name = "Latte", description = "Creamy Latte", price = 3500L)
        )

        // when
        Mockito.`when`(menuService.findMenu()).thenReturn(menuResponses)

        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/menu"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
            .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Americano"))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Latte"))
    }

    @DisplayName("[커피 메뉴 목록 조회 API] 메뉴가 없을 경우 빈배열을 반환한다.")
    @Test
    @Throws(Exception::class)
    fun testFindMenuWithNull() {

        // given
        val menuResponses: List<MenuDto>? = null

        // when
        Mockito.`when`(menuService.findMenu()).thenReturn(menuResponses)

        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/menu"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
            .andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty())
    }

    @DisplayName("[커피 메뉴 조회 API] 메뉴 상세 정보를 조회한다.")
    @Test
    @Throws(Exception::class)
    fun testFindMenuDetail() {

        // given
        val menuResponses: MenuDto = MenuDto(
            id = 1,
            name = "Americano",
            description = "Hot Americano",
            price = 3000L
        )

        // when
        Mockito.`when`(menuService.findMenu(1)).thenReturn(menuResponses)

        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/menu/1"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Americano"))

    }
}