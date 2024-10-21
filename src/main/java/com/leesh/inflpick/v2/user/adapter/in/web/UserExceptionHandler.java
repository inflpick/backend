package com.leesh.inflpick.v2.user.adapter.in.web;

import com.leesh.inflpick.v2.shared.adapter.in.web.ApiErrorResponse;
import com.leesh.inflpick.v2.shared.adapter.in.web.ApiErrorCode;
import com.leesh.inflpick.v2.user.application.port.in.exception.UserNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@Slf4j
@RestControllerAdvice
public class UserExceptionHandler {

    public static ResponseEntity<ApiErrorResponse> createResponseEntityFromApiErrorCode(HttpServletRequest request, ApiErrorCode apiErrorCode) {
        ApiErrorResponse response = ApiErrorResponse.builder()
                .timestamp(Instant.now())
                .status(apiErrorCode.getHttpStatus().value())
                .code(apiErrorCode.getCode())
                .reason(apiErrorCode.getReason())
                .action(apiErrorCode.getAction())
                .comment(apiErrorCode.getComment())
                .method(request.getMethod())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity
                .status(apiErrorCode.getHttpStatus())
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handlerUserNotFoundException(UserNotFoundException e, HttpServletRequest request) {
        log.error("UserNotFoundException: {}", e.getMessage(), e);
        return createResponseEntityFromApiErrorCode(request, UserApiErrorCode.USER_NOT_FOUND);
    }

}
