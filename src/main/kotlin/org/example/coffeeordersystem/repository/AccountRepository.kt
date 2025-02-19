package org.example.coffeeordersystem.repository

import org.example.coffeeordersystem.model.entity.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository : JpaRepository<Account, Long> {

    fun findByUsername(username: String): Account?
}