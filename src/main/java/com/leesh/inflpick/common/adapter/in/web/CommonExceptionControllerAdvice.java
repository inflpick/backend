package com.leesh.inflpick.common.adapter.in.web;

import com.leesh.inflpick.common.adapter.in.web.exception.MissingRequiredFieldsException;
import com.leesh.inflpick.common.adapter.in.web.value.ApiErrorCode;
import com.leesh.inflpick.common.adapter.in.web.value.ApiErrorResponse;
import com.leesh.inflpick.common.adapter.in.web.value.CommonApiErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
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

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiErrorResponse> handlerRuntimeException(RuntimeException e, HttpServletRequest request) {
        log.error("RuntimeException: {}", e.getMessage(), e);
        return createResponseEntityFromApiErrorCode(request, CommonApiErrorCode.SERVER_ERROR);
    }

    @ExceptionHandler(MissingRequiredFieldsException.class)
    public ResponseEntity<ApiErrorResponse> handlerMissingRequiredFieldsException(MissingRequiredFieldsException e, HttpServletRequest request) {
         log.error("MissingRequiredFieldsException: {}", e.getMessage(), e);
        return createResponseEntityFromApiErrorCode(request, CommonApiErrorCode.MISSING_REQUIRED_FIELDS);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErrorResponse> handlerHttpMessageNotReadableException(HttpMessageNotReadableException e, HttpServletRequest request) {
        log.error("HttpMessageNotReadableException: {}", e.getMessage(), e);

        return findMissingRequiredFieldsException(e)
                .map(exception -> createResponseEntityFromApiErrorCode(request, CommonApiErrorCode.MISSING_REQUIRED_FIELDS))
                .orElseGet(() -> createResponseEntityFromApiErrorCode(request, CommonApiErrorCode.INVALID_REQUEST_BODY));
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ApiErrorResponse> handlerHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e, HttpServletRequest request) {
        log.error("HttpMediaTypeNotSupportedException: {}", e.getMessage(), e);
        return createResponseEntityFromApiErrorCode(request, CommonApiErrorCode.UNSUPPORTED_HTTP_MEDIA_TYPE);
    }

    private Optional<MissingRequiredFieldsException> findMissingRequiredFieldsException(Throwable e) {
        while (e != null) {
            if (e instanceof MissingRequiredFieldsException cause) {
                return Optional.of(cause);
            } else {
                e = e.getCause();
            }
        }
        return Optional.empty();
    }

}
