package com.leesh.inflpick.v2.token.adapter.in.token;

import com.leesh.inflpick.v2.shared.adapter.in.web.ApiErrorResponse;
import com.leesh.inflpick.v2.token.application.port.in.exception.ExpiredAuthenticationCodeException;
import com.leesh.inflpick.v2.token.application.port.in.exception.ExpiredRefreshTokenException;
import com.leesh.inflpick.v2.token.application.port.in.exception.InvalidTokenException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.leesh.inflpick.v2.shared.adapter.in.web.CommonExceptionHandler.createResponseEntityFromApiErrorCode;

@Slf4j
@RestControllerAdvice
public class TokenExceptionHandler {

    @ExceptionHandler(ExpiredRefreshTokenException.class)
    public ResponseEntity<ApiErrorResponse> handlerExpiredRefreshTokenException(ExpiredRefreshTokenException e, HttpServletRequest request) {
        log.error("ExpiredRefreshTokenException: {}", e.getMessage(), e);
        return createResponseEntityFromApiErrorCode(request, TokenApiErrorCode.EXPIRED_REFRESH_TOKEN);
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ApiErrorResponse> handlerInvalidTokenException(InvalidTokenException e, HttpServletRequest request) {
        log.error("InvalidTokenException: {}", e.getMessage(), e);
        return createResponseEntityFromApiErrorCode(request, TokenApiErrorCode.INVALID_TOKEN);
    }

    @ExceptionHandler(ExpiredAuthenticationCodeException.class)
    public ResponseEntity<ApiErrorResponse> handlerExpiredAuthenticationCodeException(ExpiredAuthenticationCodeException e, HttpServletRequest request) {
        log.error("ExpiredAuthenticationCodeException: {}", e.getMessage(), e);
        return createResponseEntityFromApiErrorCode(request, TokenApiErrorCode.EXPIRED_AUTHENTICATION_CODE);
    }

    @ExceptionHandler(NotSupportedGrantTypeException.class)
    public ResponseEntity<ApiErrorResponse> handlerNotSupportedGrantTypeException(NotSupportedGrantTypeException e, HttpServletRequest request) {
        log.error("NotSupportedGrantTypeException: {}", e.getMessage(), e);
        return createResponseEntityFromApiErrorCode(request, TokenApiErrorCode.NOT_SUPPORTED_GRANT_TYPE);
    }

}
