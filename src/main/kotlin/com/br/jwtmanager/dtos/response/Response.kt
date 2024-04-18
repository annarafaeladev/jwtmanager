package com.br.jwtmanager.dtos.response

import com.fasterxml.jackson.annotation.JsonInclude
import java.time.LocalDateTime

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Response<T>(
    var data: T? = null,
    var errors: Any? = null,
) {
    fun addErrorMsgToResponse(msgError: String) {
        val error = ResponseError(msgError)
        errors = error
    }
}