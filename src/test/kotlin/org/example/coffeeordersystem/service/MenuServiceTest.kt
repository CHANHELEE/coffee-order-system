package org.example.coffeeordersystem.service

import org.assertj.core.api.Assertions.*
import org.example.coffeeordersystem.model.dto.MenuDto
import org.example.coffeeordersystem.model.entity.Menu
import org.example.coffeeordersystem.model.mapper.MenuMapper
import org.example.coffeeordersystem.model.mapper.MenuMapperImpl
import org.example.coffeeordersystem.repository.MenuRepository
import org.hamcrest.MatcherAssert.*
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Spy
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.test.context.ActiveProfiles
import java.util.*

@ExtendWith(MockitoExtension::class)
@ActiveProfiles("test")
class MenuServiceTest {

    @InjectMocks
    private lateinit var menuService: MenuService

    @Mock
    private lateinit var menuRepository: MenuRepository

    @Spy
    private var menuMapper: MenuMapper = MenuMapperImpl()

    @DisplayName("[커피 메뉴 목록 조회 API] 전체 메뉴를 조회한다.")
    @Test
    @Throws(Exception::class)
    fun testFindMenu() {

        // given
        val menuResponses: List<Menu> = listOf(
            Menu(name = "Americano", description = "Hot Americano", price = 3000L),
            Menu(name = "Latte", description = "Creamy Latte", price = 3500L)
        )
        given(menuRepository.findAll()).willReturn(menuResponses)

        //when
        val menu: List<MenuDto> = menuService.findMenu()

        //then
        assertThat(menu)
            .extracting("name", "description", "price")
            .containsExactlyInAnyOrder(
                tuple("Americano", "Hot Americano", 3000L),
                tuple("Latte", "Creamy Latte", 3500L)
            )
    }

    @DisplayName("[커피 메뉴 목록 조회 API] 메뉴가 없을 경우 빈배열을 반환한다.")
    @Test
    @Throws(Exception::class)
    fun testFindMenuWithNull() {

        // given
        val menuResponses: List<Menu> = listOf(
            Menu(name = "Americano", description = "Hot Americano", price = 3000L),
            Menu(name = "Latte", description = "Creamy Latte", price = 3500L)
        )
        given(menuRepository.findAll()).willReturn(menuResponses)

        //when
        val menu: List<MenuDto> = menuService.findMenu()

        //then
        assertThat(menu)
            .extracting("name", "description", "price")
            .containsExactlyInAnyOrder(
                tuple("Americano", "Hot Americano", 3000L),
                tuple("Latte", "Creamy Latte", 3500L)
            )
    }

    @DisplayName("[커피 메뉴 조회 API] 메뉴 상세 정보를 조회한다.")
    @Test
    @Throws(Exception::class)
    fun testFindMenuDetail() {

        // given
        val menuId = 1L
        val menuResponses: Menu = Menu(
            name = "Americano",
            description = "Hot Americano",
            price = 3000L
        )
        given(menuRepository.findById(menuId)).willReturn(Optional.of(menuResponses))

        //when
        val menu = menuService.findMenu(menuId)

        // then
        assertThat(menuResponses)
            .extracting("name", "description", "price")
            .contains("Americano", "Hot Americano", 3000L)

    }

    @DisplayName("[커피 메뉴 조회 API] 메뉴 상세 정보 조회시 조회 결과가 없을 경우 Exception")
    @Test
    @Throws(Exception::class)
    fun testFindMenuDetailWithNull() {

        // given
        val menuId = 1L
        val menuResponses: Optional<Menu> = Optional.empty()
        given(menuRepository.findById(menuId)).willReturn(menuResponses)

        // when & then
        assertThrows<RuntimeException> {
            menuService.findMenu(menuId)
        }
    }

}