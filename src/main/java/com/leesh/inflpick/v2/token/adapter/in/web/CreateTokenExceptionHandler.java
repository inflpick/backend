package com.leesh.inflpick.v2.token.adapter.in.web;

import com.leesh.inflpick.v2.common.adapter.in.web.dto.ApiErrorResponse;
import com.leesh.inflpick.v2.token.adapter.in.web.exception.NotSupportedGrantTypeException;
import com.leesh.inflpick.v2.token.adapter.in.web.constant.CreateTokenApiErrorCode;
import com.leesh.inflpick.v2.token.application.port.in.exception.ExpiredAuthenticationCodeException;
import com.leesh.inflpick.v2.token.application.port.in.exception.ExpiredRefreshTokenException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.leesh.inflpick.v2.common.adapter.in.web.exception.CommonExceptionHandler.createResponseEntityFromApiErrorCode;

@Slf4j
@Order(0)
@RestControllerAdvice(basePackageClasses = CreateTokenController.class)
public class CreateTokenExceptionHandler {

    @ExceptionHandler(NotSupportedGrantTypeException.class)
    public ResponseEntity<ApiErrorResponse> handlerNotSupportedGrantTypeException(NotSupportedGrantTypeException e, HttpServletRequest request) {
        log.error("NotSupportedGrantTypeException: {}", e.getMessage(), e);
        return createResponseEntityFromApiErrorCode(request, CreateTokenApiErrorCode.NOT_SUPPORTED_GRANT_TYPE);
    }

    @ExceptionHandler(ExpiredRefreshTokenException.class)
    public ResponseEntity<ApiErrorResponse> handlerExpiredRefreshTokenException(ExpiredRefreshTokenException e, HttpServletRequest request) {
        log.error("ExpiredRefreshTokenException: {}", e.getMessage(), e);
        return createResponseEntityFromApiErrorCode(request, CreateTokenApiErrorCode.EXPIRED_REFRESH_TOKEN);
    }

    @ExceptionHandler(ExpiredAuthenticationCodeException.class)
    public ResponseEntity<ApiErrorResponse> handlerExpiredAuthenticationCodeException(ExpiredAuthenticationCodeException e, HttpServletRequest request) {
        log.error("ExpiredAuthenticationCodeException: {}", e.getMessage(), e);
        return createResponseEntityFromApiErrorCode(request, CreateTokenApiErrorCode.EXPIRED_AUTHENTICATION_CODE);
    }


}
