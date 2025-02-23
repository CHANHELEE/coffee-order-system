package org.example.coffeeordersystem.repository

import org.example.coffeeordersystem.model.entity.Point
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PointRepository : JpaRepository<Point, Long> {
}