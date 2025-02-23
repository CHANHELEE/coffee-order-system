package org.example.coffeeordersystem.service

import org.example.coffeeordersystem.model.entity.Account
import org.example.coffeeordersystem.model.entity.Point
import org.example.coffeeordersystem.model.mapper.PointMapper
import org.example.coffeeordersystem.model.request.PointRechargeRequest
import org.example.coffeeordersystem.model.response.PointRechargeResponse
import org.example.coffeeordersystem.repository.PointRepository
import org.springframework.stereotype.Service

@Service
class PointService(
    private val pointRepository: PointRepository,
    private val pointMapper: PointMapper
) {

    fun recharge(pointRechargeRequest: PointRechargeRequest): PointRechargeResponse {
        val account: Account = Account(1L, "test", "ROLE_USER", "test", "test")
        val point: Point = Point(1L, 10000L, account)

        return pointMapper.toResponse(point)
    }
}