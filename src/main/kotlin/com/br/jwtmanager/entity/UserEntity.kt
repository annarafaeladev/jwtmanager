package com.br.jwtmanager.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import java.util.*


@Entity
@Table(name = "users")
class UserEntity(
    @Column(nullable = false, name = "full_name") val fullName: String,
    @Column(unique = true, length = 100, nullable = false, name = "email") val username: String,
    @Column(nullable = false) val password: String
)  {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uuid")
    val id: UUID = UUID.randomUUID()

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now()

    @UpdateTimestamp
    @Column(name = "updated_at")
    val updatedAt: LocalDateTime = LocalDateTime.now()


    @Column(name = "is_admin")
    val isAdmin: Boolean = false

}