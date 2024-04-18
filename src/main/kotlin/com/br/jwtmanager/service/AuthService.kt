package com.br.jwtmanager.service

import com.br.jwtmanager.infra.security.JwtProperties
import com.br.jwtmanager.dtos.auth.*
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*


@Service
class AuthService(
    private val authenticationManager: AuthenticationManager,
    private val userDetailsService: CustomUserDetailsService,
    private val tokenService: TokenService,
    private val jwtProperties: JwtProperties,
) {

    fun authentication(authRequest: AuthRequest): AuthResponse {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                authRequest.email,
                authRequest.password
            )
        )

        val user = userDetailsService.loadUserByUsername(authRequest.email)

        val accessToken = generateAccessToken(user)
        val refreshToken = generateRefreshToken(user)

        return AuthResponse(accessToken = accessToken, refreshToken = refreshToken)
    }

    fun refreshAccessToken(refreshToken: String): String? {
        val extractEmail = tokenService.extractEmail(refreshToken)
        return extractEmail?.let { email ->
            val currentUserDetails = userDetailsService.loadUserByUsername(email)

            if (!tokenService.isExpired(refreshToken))
                generateRefreshToken(currentUserDetails)
            else
                null
        }
    }


    private fun generateAccessToken(user: UserDetails): String {
        val accessToken = tokenService.generate(
            userDetails = user,
            expirationDate = Date(System.currentTimeMillis() + jwtProperties.accessTokenExpiration),

            )
        return accessToken
    }

    private fun generateRefreshToken(user: UserDetails): String {
        val accessToken = tokenService.generate(
            userDetails = user,
            expirationDate = Date(System.currentTimeMillis() + jwtProperties.refreshTokenExpiration),

            )
        return accessToken
    }
}
