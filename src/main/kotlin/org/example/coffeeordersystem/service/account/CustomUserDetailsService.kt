package org.example.coffeeordersystem.service.account

import org.example.coffeeordersystem.repository.AccountRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    private val accountRepository: AccountRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val userEntity = accountRepository.findByUsername(username)
            ?: throw UsernameNotFoundException("User not found with username: $username")

        val authority = SimpleGrantedAuthority(userEntity.role)
        return User(userEntity.username, userEntity.password, listOf(authority))
    }
}