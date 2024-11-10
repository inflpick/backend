package com.leesh.inflpick.v2.common.adapter.in.web.exception;

import com.leesh.inflpick.v2.common.adapter.in.web.constant.CommonApiErrorCode;
import com.leesh.inflpick.v2.common.adapter.in.web.dto.ApiErrorResponse;
import com.leesh.inflpick.v2.common.application.exception.ImageFormatException;
import com.leesh.inflpick.v2.common.application.exception.ThirdPartyStorageException;
import com.leesh.inflpick.v2.shared.adapter.in.web.ApiErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.time.Instant;
import java.util.Optional;

@Slf4j
@Order()
@RestControllerAdvice
public class CommonExceptionHandler {

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

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiErrorResponse> handlerHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
        log.error("HttpRequestMethodNotSupportedException: {}", e.getMessage(), e);
        return createResponseEntityFromApiErrorCode(request, CommonApiErrorCode.UNSUPPORTED_HTTP_METHOD);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ApiErrorResponse> handlerHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e, HttpServletRequest request) {
        log.error("HttpMediaTypeNotSupportedException: {}", e.getMessage(), e);
        return createResponseEntityFromApiErrorCode(request, CommonApiErrorCode.UNSUPPORTED_HTTP_MEDIA_TYPE);
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    public ResponseEntity<ApiErrorResponse> handlerMissingServletRequestPartException(MissingServletRequestPartException e, HttpServletRequest request) {
        log.error("MissingServletRequestPartException: {}", e.getMessage(), e);
        return createResponseEntityFromApiErrorCode(request, CommonApiErrorCode.MISSING_REQUEST_PART);
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<ApiErrorResponse> handlerAuthorizationDeniedException(AuthorizationDeniedException e, HttpServletRequest request) {
        log.error("AuthorizationDeniedException: {}", e.getMessage(), e);
        return createResponseEntityFromApiErrorCode(request, CommonApiErrorCode.NOT_ADMIN_USER);
    }

    @ExceptionHandler(ThirdPartyStorageException.class)
    public ResponseEntity<ApiErrorResponse> handlerThirdPartyStorageException(ThirdPartyStorageException e, HttpServletRequest request) {
        log.error("ThirdPartyStorageException: {}", e.getMessage(), e);
        return createResponseEntityFromApiErrorCode(request, CommonApiErrorCode.FILE_UPLOAD_FAILED);
    }

    @ExceptionHandler(ImageFormatException.class)
    public ResponseEntity<ApiErrorResponse> handlerImageFormatException(ImageFormatException e, HttpServletRequest request) {
        log.error("ImageFormatException: {}", e.getMessage(), e);
        return createResponseEntityFromApiErrorCode(request, CommonApiErrorCode.NOT_IMAGE_TYPE);
    }
}
