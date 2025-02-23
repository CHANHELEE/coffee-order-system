package org.example.coffeeordersystem.controller

import org.example.coffeeordersystem.common.ControllerTestSupporter
import org.example.coffeeordersystem.model.entity.Account
import org.example.coffeeordersystem.model.request.PointRechargeRequest
import org.example.coffeeordersystem.model.response.PointRechargeResponse
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.kotlin.any
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.util.*

@WebMvcTest(
    controllers = [PointController::class]
)
@ActiveProfiles("test")
@WithMockUser
class PointControllerTest : ControllerTestSupporter() {

    @BeforeEach
    fun setUp() {

        given(currentUserHandlerMethodArgumentResolver.supportsParameter(any()))
            .willReturn(true)

        val mockedAccount: Account = Account(1L, "test", "ROLE_USER", "test", "test")
        given(
            currentUserHandlerMethodArgumentResolver.resolveArgument(
                any(),
                any(),
                any(),
                any()
            )
        ).willReturn(mockedAccount)
    }

    @DisplayName("[포인트 충전 API] 특정 사용자의 포인트를 충전한다.")
    @Test
    @Throws(Exception::class)
    fun testRechargePoint() {

        // given
        val pointId = 1L
        val accountId = 1L
        val rechargingPoint = 10000L
        val pointRechargeResponse = PointRechargeResponse(pointId, rechargingPoint, accountId)
        val pointRechargeRequest = PointRechargeRequest(accountId, rechargingPoint)
        val requestBody = objectMapper.writeValueAsString(pointRechargeRequest)

        given(pointService.recharge(pointRechargeRequest)).willReturn(pointRechargeResponse)

        // when && then
        mockMvc.perform(
            MockMvcRequestBuilders.patch("/api/point/recharge")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody) )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.point").value("10000"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.accountId").value("1"))
    }

    @DisplayName("[포인트 충전 API] 특정 사용자의 포인트 충전시 0보다 같거나 작은 값을 충전하면 충전에 실패한다.")
    @Test
    @Throws(Exception::class)
    fun testRechargePointWithPointLowerThanZero() {

        // given
        val accountId = 1L
        val rechargingPoint = -1000L
        val pointRechargeRequest = PointRechargeRequest(accountId, rechargingPoint)
        val requestBody = objectMapper.writeValueAsString(pointRechargeRequest)

        // when && then
        mockMvc.perform(
            MockMvcRequestBuilders.patch("/api/point/recharge")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody) )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
    }

    @DisplayName("[포인트 충전 API] 특정 사용자의 포인트 충전시 로그인한 사용자와 충전 하고자 하는 사용자의 식별값이 다르면 충전에 실패한다.")
    @Test
    @Throws(Exception::class)
    fun testRechargePointWithConflictAccount() {

        // given
        val accountId = 2L
        val rechargingPoint = 1000L
        val pointRechargeRequest = PointRechargeRequest(accountId, rechargingPoint)
        val requestBody = objectMapper.writeValueAsString(pointRechargeRequest)

        // when && then
        mockMvc.perform(
            MockMvcRequestBuilders.patch("/api/point/recharge")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody) )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
    }

}