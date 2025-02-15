package org.example.coffeeordersystem

import org.example.coffeeordersystem.service.MenuService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc

abstract class ControllerTestSupporter {

    @Autowired
    protected lateinit var mockMvc: MockMvc

    @MockitoBean
    protected lateinit var menuService: MenuService

}