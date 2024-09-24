package com.leesh.inflpick.common.adapter.in.web;

import com.leesh.inflpick.common.adapter.in.web.exception.InvalidSortParameterException;
import com.leesh.inflpick.common.adapter.in.web.exception.MissingRequiredFieldsException;
import com.leesh.inflpick.common.adapter.in.web.value.ApiErrorCode;
import com.leesh.inflpick.common.adapter.in.web.value.ApiErrorResponse;
import com.leesh.inflpick.common.adapter.in.web.value.CommonApiErrorCode;
import com.leesh.inflpick.common.port.in.exception.InvalidDirectionException;
import com.leesh.inflpick.common.port.in.exception.NotImageTypeException;
import com.leesh.inflpick.common.port.in.exception.InvalidPageNumberException;
import com.leesh.inflpick.common.port.in.exception.InvalidPageSizeException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.Instant;
import java.util.Optional;

@Slf4j
@Order()
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
                .contentType(MediaType.APPLICATION_JSON)
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

    @ExceptionHandler(MissingServletRequestPartException.class)
    public ResponseEntity<ApiErrorResponse> handlerMissingServletRequestPartException(MissingServletRequestPartException e, HttpServletRequest request) {
        log.error("MissingServletRequestPartException: {}", e.getMessage(), e);
        return createResponseEntityFromApiErrorCode(request, CommonApiErrorCode.MISSING_REQUEST_PART);
    }

    @ExceptionHandler(NotImageTypeException.class)
    public ResponseEntity<ApiErrorResponse> handlerNotImageTypeException(NotImageTypeException e, HttpServletRequest request) {
        log.error("NotImageTypeException: {}", e.getMessage(), e);
        return createResponseEntityFromApiErrorCode(request, CommonApiErrorCode.NOT_IMAGE_TYPE);
    }

    @ExceptionHandler(InvalidPageNumberException.class)
    public ResponseEntity<ApiErrorResponse> handlerWrongPageException(InvalidPageNumberException e, HttpServletRequest request) {
        log.error("WrongPageException: {}", e.getMessage(), e);
        return createResponseEntityFromApiErrorCode(request, CommonApiErrorCode.INVALID_PAGE_VALUE);
    }

    @ExceptionHandler(InvalidPageSizeException.class)
    public ResponseEntity<ApiErrorResponse> handlerWrongPageSizeException(InvalidPageSizeException e, HttpServletRequest request) {
        log.error("WrongPageSizeException: {}", e.getMessage(), e);
        return createResponseEntityFromApiErrorCode(request, CommonApiErrorCode.INVALID_PAGE_SIZE_VALUE);
    }

    @ExceptionHandler(InvalidDirectionException.class)
    public ResponseEntity<ApiErrorResponse> handlerInvalidDirectionException(InvalidDirectionException e, HttpServletRequest request) {
        log.error("InvalidDirectionException: {}", e.getMessage(), e);
        return createResponseEntityFromApiErrorCode(request, CommonApiErrorCode.INVALID_SORT_DIRECTION);
    }

    @ExceptionHandler(InvalidSortParameterException.class)
    public ResponseEntity<ApiErrorResponse> handlerInvalidSortParameterException(InvalidSortParameterException e, HttpServletRequest request) {
        log.error("InvalidSortParameterException: {}", e.getMessage(), e);
        return createResponseEntityFromApiErrorCode(request, CommonApiErrorCode.INVALID_SORT_PARAMETER);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiErrorResponse> handlerNoResourceFoundException(NoResourceFoundException e, HttpServletRequest request) {
        log.error("NoResourceFoundException: {}", e.getMessage(), e);
        return createResponseEntityFromApiErrorCode(request, CommonApiErrorCode.NOT_FOUND_API_URL);
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
