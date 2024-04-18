package com.br.jwtmanager.repository

import com.br.jwtmanager.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<UserEntity, UUID> {
    fun findByUsername(email: String) : Optional<UserEntity>
}