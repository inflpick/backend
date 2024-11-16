package com.leesh.inflpick.common.adapter.in.web;

import com.leesh.inflpick.common.adapter.in.web.dto.ApiErrorResponse;
import com.leesh.inflpick.common.adapter.in.web.exception.*;
import com.leesh.inflpick.common.application.exception.ThirdPartyStorageException;
import com.leesh.inflpick.common.domain.ErrorCode;
import com.leesh.inflpick.influencer.application.exception.InfluencerNotFoundException;
import com.leesh.inflpick.influencer.application.exception.InvalidProfileImageException;
import com.leesh.inflpick.influencer.domain.exception.*;
import com.leesh.inflpick.keyword.application.exception.AlreadyExistKeywordNameException;
import com.leesh.inflpick.keyword.application.exception.KeywordNotFoundException;
import com.leesh.inflpick.keyword.domain.exception.KeywordHexColorException;
import com.leesh.inflpick.keyword.domain.exception.KeywordNameFormatException;
import com.leesh.inflpick.product.adapter.in.web.exception.NotSupportOnlineStorePlatformException;
import com.leesh.inflpick.product.application.exception.InvalidProductImageFormat;
import com.leesh.inflpick.product.application.exception.ProductNotFoundException;
import com.leesh.inflpick.token.adapter.in.web.exception.NotSupportedGrantTypeException;
import com.leesh.inflpick.token.application.port.in.exception.ExpiredAuthenticationCodeException;
import com.leesh.inflpick.token.application.port.in.exception.ExpiredRefreshTokenException;
import com.leesh.inflpick.user.application.exception.UserNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.util.Optional;

@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {

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
        ErrorCode errorCode = new ServiceUnavailableException();
        ApiErrorResponse response = ApiErrorResponse.create(request, errorCode);
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(response);
    }

    @ExceptionHandler(MissingRequiredFieldsException.class)
    public ResponseEntity<ApiErrorResponse> handlerMissingRequiredFieldsException(MissingRequiredFieldsException e, HttpServletRequest request) {
        log.error("MissingRequiredFieldsException: {}", e.getMessage(), e);
        ErrorCode errorCode = new MissingRequiredFieldsException();
        ApiErrorResponse response = ApiErrorResponse.create(request, errorCode);
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(response);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErrorResponse> handlerHttpMessageNotReadableException(HttpMessageNotReadableException e, HttpServletRequest request) {
        log.error("HttpMessageNotReadableException: {}", e.getMessage(), e);
        return findMissingRequiredFieldsException(e)
                .map(exception -> {
                    MissingRequiredFieldsException apiErrorCode = new MissingRequiredFieldsException();
                    ApiErrorResponse response = ApiErrorResponse.create(request, apiErrorCode);
                    return ResponseEntity.status(apiErrorCode.getHttpStatus())
                            .body(response);
                })
                .orElseGet(() -> {
                    InvalidRequestBodyException apiErrorCode1 = new InvalidRequestBodyException();
                    ApiErrorResponse response = ApiErrorResponse.create(request, apiErrorCode1);
                    return ResponseEntity.status(apiErrorCode1.getHttpStatus())
                            .body(response);
                });
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiErrorResponse> handlerHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
        log.error("HttpRequestMethodNotSupportedException: {}", e.getMessage(), e);
        NotSupportHttpMethodException apiErrorCode = new NotSupportHttpMethodException();
        ApiErrorResponse response = ApiErrorResponse.create(request, apiErrorCode);
        return ResponseEntity.status(apiErrorCode.getHttpStatus())
                .body(response);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ApiErrorResponse> handlerHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e, HttpServletRequest request) {
        log.error("HttpMediaTypeNotSupportedException: {}", e.getMessage(), e);
        NotSupportHttpMediaTypeException apiErrorCode = new NotSupportHttpMediaTypeException();
        ApiErrorResponse response = ApiErrorResponse.create(request, apiErrorCode);
        return ResponseEntity.status(apiErrorCode.getHttpStatus())
                .body(response);
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    public ResponseEntity<ApiErrorResponse> handlerMissingServletRequestPartException(MissingServletRequestPartException e, HttpServletRequest request) {
        log.error("MissingServletRequestPartException: {}", e.getMessage(), e);
        MissingRequestPartException apiErrorCode = new MissingRequestPartException();
        ApiErrorResponse response = ApiErrorResponse.create(request, apiErrorCode);
        return ResponseEntity.status(apiErrorCode.getHttpStatus())
                .body(response);
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<ApiErrorResponse> handlerAuthorizationDeniedException(AuthorizationDeniedException e, HttpServletRequest request) {
        log.error("AuthorizationDeniedException: {}", e.getMessage(), e);
        ErrorCode errorCode = new NotAdminUserException();
        ApiErrorResponse response = ApiErrorResponse.create(request, errorCode);
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(response);
    }

    @ExceptionHandler(ThirdPartyStorageException.class)
    public ResponseEntity<ApiErrorResponse> handlerThirdPartyStorageException(ThirdPartyStorageException e, HttpServletRequest request) {
        log.error("ThirdPartyStorageException: {}", e.getMessage(), e);
        ErrorCode errorCode = new ThirdPartyStorageException(e.getMessage(), e);
        ApiErrorResponse response = ApiErrorResponse.create(request, errorCode);
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(response);
    }

    @ExceptionHandler(NotImageTypeException.class)
    public ResponseEntity<ApiErrorResponse> handlerImageFormatException(NotImageTypeException e, HttpServletRequest request) {
        log.error("NotImageTypeException: {}", e.getMessage(), e);
        ErrorCode errorCode = new NotImageTypeException();
        ApiErrorResponse response = ApiErrorResponse.create(request, errorCode);
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(response);
    }

    /* Influencers */
    @ExceptionHandler(InfluencerNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handlerInfluencerNotFoundException(InfluencerNotFoundException e, HttpServletRequest request) {
        log.error("InfluencerNotFoundException: {}", e.getMessage(), e);
        ApiErrorResponse response = ApiErrorResponse.create(request, e);
        return ResponseEntity.status(e.getHttpStatus())
                .body(response);
    }

    @ExceptionHandler(NotSupportedSnsPlatformException.class)
    public ResponseEntity<ApiErrorResponse> handlerNotSupportedSnsPlatformException(NotSupportedSnsPlatformException e, HttpServletRequest request) {
        log.error("NotSupportedSnsPlatformException: {}", e.getMessage(), e);
        ApiErrorResponse response = ApiErrorResponse.create(request, e);
        return ResponseEntity.status(e.getHttpStatus())
                .body(response);
    }

    @ExceptionHandler(InvalidProfileImageException.class)
    public ResponseEntity<ApiErrorResponse> handlerInvalidProfileImageException(InvalidProfileImageException e, HttpServletRequest request) {
        log.error("InvalidProfileImageException: {}", e.getMessage(), e);
        ApiErrorResponse response = ApiErrorResponse.create(request, e);
        return ResponseEntity.status(e.getHttpStatus())
                .body(response);
    }

    @ExceptionHandler(InfluencerDescriptionFormatException.class)
    public ResponseEntity<ApiErrorResponse> handlerInfluencerDescriptionFormatException(InfluencerDescriptionFormatException e, HttpServletRequest request) {
        log.error("InfluencerDescriptionFormatException: {}", e.getMessage(), e);
        ApiErrorResponse response = ApiErrorResponse.create(request, e);
        return ResponseEntity.status(e.getHttpStatus())
                .body(response);
    }

    @ExceptionHandler(InfluencerIntroductionFormatException.class)
    public ResponseEntity<ApiErrorResponse> handlerInfluencerIntroductionFormatException(InfluencerIntroductionFormatException e, HttpServletRequest request) {
        log.error("InfluencerIntroductionFormatException: {}", e.getMessage(), e);
        ApiErrorResponse response = ApiErrorResponse.create(request, e);
        return ResponseEntity.status(e.getHttpStatus())
                .body(response);
    }

    @ExceptionHandler(InfluencerNameFormatException.class)
    public ResponseEntity<ApiErrorResponse> handlerInfluencerNameFormatException(InfluencerNameFormatException e, HttpServletRequest request) {
        log.error("InfluencerNameFormatException: {}", e.getMessage(), e);
        ApiErrorResponse response = ApiErrorResponse.create(request, e);
        return ResponseEntity.status(e.getHttpStatus())
                .body(response);
    }

    @ExceptionHandler(MaximumInfluencerKeywordSizeException.class)
    public ResponseEntity<ApiErrorResponse> handlerMaximumInfluencerKeywordSizeException(MaximumInfluencerKeywordSizeException e, HttpServletRequest request) {
        log.error("MaximumInfluencerKeywordSizeException: {}", e.getMessage(), e);
        ApiErrorResponse response = ApiErrorResponse.create(request, e);
        return ResponseEntity.status(e.getHttpStatus())
                .body(response);
    }

    /* Keywords */
    @ExceptionHandler(AlreadyExistKeywordNameException.class)
    public ResponseEntity<ApiErrorResponse> handlerAlreadyExistKeywordNameException(AlreadyExistKeywordNameException e, HttpServletRequest request) {
        log.error("AlreadyExistKeywordNameException: {}", e.getMessage(), e);
        ApiErrorResponse response = ApiErrorResponse.create(request, e);
        return ResponseEntity.status(e.getHttpStatus())
                .body(response);
    }

    @ExceptionHandler(KeywordNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handlerKeywordNotFoundException(KeywordNotFoundException e, HttpServletRequest request) {
        log.error("KeywordNotFoundException: {}", e.getMessage(), e);
        ApiErrorResponse response = ApiErrorResponse.create(request, e);
        return ResponseEntity.status(e.getHttpStatus())
                .body(response);
    }

    @ExceptionHandler(KeywordNameFormatException.class)
    public ResponseEntity<ApiErrorResponse> handlerKeywordNameFormatException(KeywordNameFormatException e, HttpServletRequest request) {
        log.error("KeywordNameFormatException: {}", e.getMessage(), e);
        ApiErrorResponse response = ApiErrorResponse.create(request, e);
        return ResponseEntity.status(e.getHttpStatus())
                .body(response);
    }

    @ExceptionHandler(KeywordHexColorException.class)
    public ResponseEntity<ApiErrorResponse> handlerKeywordHexColorException(KeywordHexColorException e, HttpServletRequest request) {
        log.error("KeywordHexColorException: {}", e.getMessage(), e);
        ApiErrorResponse response = ApiErrorResponse.create(request, e);
        return ResponseEntity.status(e.getHttpStatus())
                .body(response);
    }

    /* Products */
    @ExceptionHandler(NotSupportOnlineStorePlatformException.class)
    public ResponseEntity<ApiErrorResponse> handlerNotSupportOnlineStorePlatformException(NotSupportOnlineStorePlatformException e, HttpServletRequest request) {
        log.error("NotSupportOnlineStorePlatformException: {}", e.getMessage(), e);
        ApiErrorResponse response = ApiErrorResponse.create(request, e);
        return ResponseEntity.status(e.getHttpStatus())
                .body(response);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handlerProductNotFoundException(ProductNotFoundException e, HttpServletRequest request) {
        log.error("ProductNotFoundException: {}", e.getMessage(), e);
        ApiErrorResponse response = ApiErrorResponse.create(request, e);
        return ResponseEntity.status(e.getHttpStatus())
                .body(response);
    }

    @ExceptionHandler(InvalidProductImageFormat.class)
    public ResponseEntity<ApiErrorResponse> handlerInvalidProductImageFormat(InvalidProductImageFormat e, HttpServletRequest request) {
        log.error("InvalidProductImageFormat: {}", e.getMessage(), e);
        ApiErrorResponse response = ApiErrorResponse.create(request, e);
        return ResponseEntity.status(e.getHttpStatus())
                .body(response);
    }

    /* Reviews */

    /* Tokens */
    @ExceptionHandler(NotSupportedGrantTypeException.class)
    public ResponseEntity<ApiErrorResponse> handlerNotSupportedGrantTypeException(NotSupportedGrantTypeException e, HttpServletRequest request) {
        log.error("NotSupportedGrantTypeException: {}", e.getMessage(), e);
        ApiErrorResponse response = ApiErrorResponse.create(request, e);
        return ResponseEntity.status(e.getHttpStatus())
                .body(response);
    }

    @ExceptionHandler(ExpiredRefreshTokenException.class)
    public ResponseEntity<ApiErrorResponse> handlerExpiredRefreshTokenException(ExpiredRefreshTokenException e, HttpServletRequest request) {
        log.error("ExpiredRefreshTokenException: {}", e.getMessage(), e);
        ApiErrorResponse response = ApiErrorResponse.create(request, e);
        return ResponseEntity.status(e.getHttpStatus())
                .body(response);
    }

    @ExceptionHandler(ExpiredAuthenticationCodeException.class)
    public ResponseEntity<ApiErrorResponse> handlerExpiredAuthenticationCodeException(ExpiredAuthenticationCodeException e, HttpServletRequest request) {
        log.error("ExpiredAuthenticationCodeException: {}", e.getMessage(), e);
        ApiErrorResponse response = ApiErrorResponse.create(request, e);
        return ResponseEntity.status(e.getHttpStatus())
                .body(response);
    }

    /* Users */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handlerUserNotFoundException(UserNotFoundException e, HttpServletRequest request) {
        log.error("UserNotFoundException: {}", e.getMessage(), e);
        ApiErrorResponse response = ApiErrorResponse.create(request, e);
        return ResponseEntity.status(e.getHttpStatus())
                .body(response);
    }
}
