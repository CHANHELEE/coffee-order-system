package org.example.coffeeordersystem

import org.example.coffeeordersystem.config.security.JwtAuthenticationFilter
import org.example.coffeeordersystem.config.security.JwtTokenProvider
import org.example.coffeeordersystem.config.security.SecurityConfig
import org.example.coffeeordersystem.service.MenuService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Import
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc


@Import(SecurityConfig::class, JwtTokenProvider::class, JwtAuthenticationFilter::class)
abstract class ControllerTestSupporter {

    @Autowired
    protected lateinit var mockMvc: MockMvc

    @MockitoBean
    protected lateinit var menuService: MenuService

}