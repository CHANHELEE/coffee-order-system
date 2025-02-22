package org.example.coffeeordersystem.common

import org.example.coffeeordersystem.common.resolver.CurrentUserHandlerMethodArgumentResolver
import org.example.coffeeordersystem.config.SecurityConfigTest
import org.example.coffeeordersystem.config.security.JwtTokenProvider
import org.example.coffeeordersystem.service.MenuService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Import
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc

@Import(SecurityConfigTest::class, JwtTokenProvider::class)
abstract class ControllerTestSupporter {

    @Autowired
    protected lateinit var mockMvc: MockMvc

    @MockitoBean
    protected lateinit var menuService: MenuService

    @MockitoBean
    protected lateinit var currentUserHandlerMethodArgumentResolver: CurrentUserHandlerMethodArgumentResolver;

}