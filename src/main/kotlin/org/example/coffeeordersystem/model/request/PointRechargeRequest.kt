package org.example.coffeeordersystem.model.request

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull


data class PointRechargeRequest(

    @field:NotNull(message = "사용자 식별값은 필수 값 입니다.")
    val accountId: Long?,

    @field:NotNull(message = "포인트 충전값은 필수 값 입니다.")
    @field:Min(value = 1L, message = "포인트 충전값은 1원 이상이여야 합니다.")
    val point: Long?
)
