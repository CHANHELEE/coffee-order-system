package org.example.coffeeordersystem.model.mapper

import org.example.coffeeordersystem.model.entity.Point
import org.example.coffeeordersystem.model.response.PointRechargeResponse
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface PointMapper {

    @Mapping(target = "accountId", source = "account.id")
    fun toResponse(point: Point): PointRechargeResponse
}