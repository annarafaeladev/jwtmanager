package com.br.jwtmanager.controller

import com.br.jwtmanager.dtos.response.*
import com.br.jwtmanager.dtos.auth.*
import com.br.jwtmanager.dtos.refreshToken.RefreshTokenDto
import com.br.jwtmanager.service.AuthService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.server.ResponseStatusException

@Controller
@RequestMapping("api/auth")
class AuthenticationController
(
    private val authService: AuthService,
) {

    @PostMapping
    fun authenticate(@RequestBody authRequest: AuthRequest): ResponseEntity<Response<AuthResponse>> {
        val response: Response<AuthResponse> = Response(authService.authentication(authRequest))

        return ResponseEntity.ok(response)

    }

    @PostMapping("/refresh")
    fun refreshAccessToken(@RequestBody request: RefreshTokenDto): ResponseEntity<Response<RefreshTokenDto>> {
        val token = authService.refreshAccessToken(request.token)
            ?: throw ResponseStatusException(HttpStatus.FORBIDDEN, "Invalid request")

        val response: Response<RefreshTokenDto> = Response(RefreshTokenDto(token))

        return ResponseEntity.ok(response)

    }
}