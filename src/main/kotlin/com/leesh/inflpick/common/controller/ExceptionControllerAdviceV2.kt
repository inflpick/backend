package com.leesh.inflpick.common.controller

import com.leesh.inflpick.common.controller.dto.ErrorResponse
import jakarta.servlet.http.HttpServletRequest
import org.springframework.core.annotation.Order
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@Order
@RestControllerAdvice
class ExceptionControllerAdviceV2 {

    /**
     * COMMON EXCEPTION HANDLING
     */
    @ExceptionHandler(MissingServletRequestParameterException::class)
    fun exceptionHandler(e: MissingServletRequestParameterException, request: HttpServletRequest): ResponseEntity<ErrorResponse> {
        val response = ErrorResponse.fromException(ErrorCode.MISSING_REQUIRED_PARAMETER, request)
        return ResponseEntity
            .status(response.status)
            .body(response)
    }

}