package com.br.jwtmanager.dtos.auth

data class AuthRequest (
    val email: String,
    val password: String
)
