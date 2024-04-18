package com.br.jwtmanager.dtos.auth

data class AuthResponse(
    val accessToken: String,
    val refreshToken: String
)
