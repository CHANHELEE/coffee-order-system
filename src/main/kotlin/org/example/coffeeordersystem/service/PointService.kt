package org.example.coffeeordersystem.service

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

    fun recharge(pointRechargeRequest: PointRechargeRequest): PointRechargeResponse =
        pointRepository.findByAccountId(pointRechargeRequest.accountId!!)
            .apply { point += pointRechargeRequest.point!! }
            .let { pointRepository.save(it) }
            .let { pointMapper.toResponse(it) }
}