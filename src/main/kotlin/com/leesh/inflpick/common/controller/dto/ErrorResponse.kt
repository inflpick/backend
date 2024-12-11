package com.leesh.inflpick.common.controller.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.leesh.inflpick.common.controller.ErrorCode
import jakarta.servlet.http.HttpServletRequest
import java.time.Instant

class ErrorResponse(
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    val timestamp: Instant,
    val status: Int,
    val code: String,
    val reason: String,
    val action: String,
    val method: String,
    val path: String,
    val comment: String,
) {
    companion object {
        fun fromException(errorCode: ErrorCode, request: HttpServletRequest): ErrorResponse {
            return ErrorResponse(
                timestamp = Instant.now(),
                status = errorCode.status,
                code = errorCode.code,
                reason = errorCode.reason,
                action = errorCode.action,
                method = request.method,
                path = request.requestURI,
                comment = errorCode.comment,
            )
        }
    }
}
