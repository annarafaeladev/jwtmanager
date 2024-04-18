package com.br.jwtmanager.service

import com.br.jwtmanager.entity.UserEntity
import com.br.jwtmanager.repository.UserRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(private val userRepository: UserRepository) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByUsername(username)

        if (user.isEmpty) {
            throw UsernameNotFoundException("User not found by username $username")
        }

        return user.get().toUserDetails()
    }

    private fun UserEntity.toUserDetails(): UserDetails {
        val roles = mutableListOf<String>()
        if (this.isAdmin) {
            roles.add("ADMIN")
        } else {
            roles.add("USER")
        }
        return User.builder()
            .username(this.username)
            .password(this.password)
            .roles(*roles.toTypedArray())
            .build()
    }
}