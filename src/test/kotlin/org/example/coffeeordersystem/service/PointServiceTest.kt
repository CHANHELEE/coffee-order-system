package org.example.coffeeordersystem.service

import org.assertj.core.api.Assertions.*
import org.example.coffeeordersystem.model.entity.Account
import org.example.coffeeordersystem.model.entity.Point
import org.example.coffeeordersystem.model.mapper.PointMapper
import org.example.coffeeordersystem.model.mapper.PointMapperImpl
import org.example.coffeeordersystem.model.request.PointRechargeRequest
import org.example.coffeeordersystem.repository.PointRepository
import org.hamcrest.MatcherAssert.*
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
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
class PointServiceTest {

    @InjectMocks
    private lateinit var pointService: PointService

    @Mock
    private lateinit var pointRepository: PointRepository

    @Spy
    private var pointMapper: PointMapper = PointMapperImpl()

    @DisplayName("[포인트 충전 API] 특정 사용자의 포인트를 충전한다")
    @Test
    @Throws(Exception::class)
    fun testRechargePoint() {

        // given
        val accountId = 1L
        val pointId = 1L
        val rechargingPoint = 1000L
        val accountPoint = 1000L

        val pointRechargeRequest = PointRechargeRequest(accountId, rechargingPoint)
        val account = Account(accountId, "test", "ROLE_USER", "test", "test")
        val returnedPointWithFind = Point(pointId, accountPoint, account)
        val returnedPointWithSave = Point(pointId, accountPoint + rechargingPoint, account)

        given(pointRepository.findByAccountId(accountId)).willReturn(returnedPointWithFind)
        given(pointRepository.save(returnedPointWithFind)).willReturn(returnedPointWithSave)

        //when
        val pointRechargeResponse = pointService.recharge(pointRechargeRequest)

        //then
        assertThat(pointRechargeResponse)
            .extracting("id", "point", "accountId")
            .contains(pointId, accountPoint + rechargingPoint, accountId)

    }

}