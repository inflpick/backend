package com.leesh.inflpick.common.adapter.in.web;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.Optional;

@Slf4j
@RestControllerAdvice
public class CommonExceptionControllerAdvice {

    public static ResponseEntity<ApiErrorResponse> createResponseEntityFromApiErrorCode(HttpServletRequest request, ApiErrorCode apiErrorCode) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
                Instant.now(),
                apiErrorCode.httpStatus().value(),
                apiErrorCode.code(),
                apiErrorCode.reason(),
                apiErrorCode.action(),
                request.getMethod(),
                request.getRequestURI()
        );
        return ResponseEntity
                .status(apiErrorCode.httpStatus())
                .body(apiErrorResponse);
    }

    @ExceptionHandler(MissingRequiredFieldsException.class)
    public ResponseEntity<ApiErrorResponse> handlerMissingRequiredFieldsException(MissingRequiredFieldsException e, HttpServletRequest request) {
        log.error("MissingRequiredFieldsException: {}", e.getMessage());
        ApiErrorCode apiErrorCode = e.getApiErrorCode();
        return createResponseEntityFromApiErrorCode(request, apiErrorCode);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErrorResponse> handlerHttpMessageNotReadableException(HttpMessageNotReadableException e, HttpServletRequest request) {
        log.error("HttpMessageNotReadableException: {}", e.getMessage());

        return findMissingRequiredFieldsException(e.getCause())
                .map(exception -> createResponseEntityFromApiErrorCode(request, exception.getApiErrorCode()))
                .orElseGet(() -> createResponseEntityFromApiErrorCode(request, CommonApiErrorCode.INVALID_REQUEST_BODY));
    }

    private Optional<MissingRequiredFieldsException> findMissingRequiredFieldsException(Throwable e) {
        while (e != null) {
            if (e instanceof MissingRequiredFieldsException cause) {
                return Optional.of(cause);
            }
            e = e.getCause();
        }
        return Optional.empty();
    }

}
