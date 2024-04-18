package com.br.jwtmanager.dtos.response

import com.fasterxml.jackson.annotation.JsonInclude
import org.jetbrains.annotations.NotNull
import java.time.LocalDateTime

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ResponseError(
    @field:NotNull("Details cannot be null")
    var details: String,
    val timestamp: LocalDateTime = LocalDateTime.now(),
)