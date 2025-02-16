package org.example.coffeeordersystem.repository

import org.example.coffeeordersystem.model.entity.Menu
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MenuRepository : JpaRepository<Menu, Long> {
}