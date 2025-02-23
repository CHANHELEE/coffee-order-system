package org.example.coffeeordersystem.repository

import jakarta.persistence.LockModeType
import org.example.coffeeordersystem.model.entity.Point
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.stereotype.Repository

@Repository
interface PointRepository : JpaRepository<Point, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    fun findByAccountId(accountId: Long) : Point
}