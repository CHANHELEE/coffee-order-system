package org.example.coffeeordersystem.controller

import jakarta.validation.Valid
import org.apache.coyote.BadRequestException
import org.example.coffeeordersystem.common.annotation.CurrentUser
import org.example.coffeeordersystem.model.entity.Account
import org.example.coffeeordersystem.model.request.PointRechargeRequest
import org.example.coffeeordersystem.model.response.PointRechargeResponse
import org.example.coffeeordersystem.service.PointService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/point")
class PointController(
    private val pointService: PointService
) {

    @PatchMapping("/recharge")
    fun recharge(
        @CurrentUser account: Account,
        @RequestBody @Valid pointRechargeRequest: PointRechargeRequest
    ): ResponseEntity<PointRechargeResponse> =
        account.takeIf { account.id == pointRechargeRequest.accountId }
            ?.run { ResponseEntity.ok(pointService.recharge(pointRechargeRequest)) }
            ?: ResponseEntity.badRequest().build()

}