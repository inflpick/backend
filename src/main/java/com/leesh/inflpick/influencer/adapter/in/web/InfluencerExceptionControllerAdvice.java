package com.leesh.inflpick.influencer.adapter.in.web;

import com.leesh.inflpick.common.adapter.in.web.value.ApiErrorResponse;
import com.leesh.inflpick.common.port.out.exception.InvalidFileRequestException;
import com.leesh.inflpick.common.port.out.exception.ThirdPartyStorageException;
import com.leesh.inflpick.influencer.adapter.in.web.value.InfluencerCreateApiErrorCode;
import com.leesh.inflpick.influencer.adapter.in.web.value.InfluencerReadApiErrorCode;
import com.leesh.inflpick.influencer.port.out.InfluencerNotFoundException;
import com.leesh.inflpick.influencer.core.domain.exception.InfluencerDescriptionValidationFailedException;
import com.leesh.inflpick.influencer.core.domain.exception.InfluencerIntroductionValidationFailedException;
import com.leesh.inflpick.influencer.core.domain.exception.InfluencerNameValidationFailedException;
import com.leesh.inflpick.influencer.core.domain.exception.InvalidSocialMediaPlatformException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.leesh.inflpick.common.adapter.in.web.CommonExceptionControllerAdvice.createResponseEntityFromApiErrorCode;

@Slf4j
@Order(PriorityOrdered.HIGHEST_PRECEDENCE)
@RestControllerAdvice(basePackageClasses = InfluencerController.class)
public class InfluencerExceptionControllerAdvice {

    @ExceptionHandler(InfluencerNameValidationFailedException.class)
    public ResponseEntity<ApiErrorResponse> handlerInfluencerNameValidationFailedException(InfluencerNameValidationFailedException e, HttpServletRequest request) {
        log.warn("InfluencerNameValidationFailedException: {}", e.getMessage(), e);
        InfluencerCreateApiErrorCode apiErrorCode = InfluencerCreateApiErrorCode.INFLUENCER_NAME_VALIDATION_FAILED;
        return createResponseEntityFromApiErrorCode(request, apiErrorCode);
    }

    @ExceptionHandler(InfluencerIntroductionValidationFailedException.class)
    public ResponseEntity<ApiErrorResponse> handlerInfluencerIntroductionValidationFailedException(InfluencerIntroductionValidationFailedException e, HttpServletRequest request) {
        log.warn("InfluencerIntroductionValidationFailedException: {}", e.getMessage(), e);
        InfluencerCreateApiErrorCode apiErrorCode = InfluencerCreateApiErrorCode.INFLUENCER_INTRODUCTION_VALIDATION_FAILED;
        return createResponseEntityFromApiErrorCode(request, apiErrorCode);
    }

    @ExceptionHandler(InfluencerDescriptionValidationFailedException.class)
    public ResponseEntity<ApiErrorResponse> handlerInfluencerDescriptionValidationFailedException(InfluencerDescriptionValidationFailedException e, HttpServletRequest request) {
        log.warn("InfluencerDescriptionValidationFailedException: {}", e.getMessage(), e);
        InfluencerCreateApiErrorCode apiErrorCode = InfluencerCreateApiErrorCode.INFLUENCER_DESCRIPTION_VALIDATION_FAILED;
        return createResponseEntityFromApiErrorCode(request, apiErrorCode);
    }

    @ExceptionHandler(InvalidSocialMediaPlatformException.class)
    public ResponseEntity<ApiErrorResponse> handlerInvalidSocialMediaPlatformException(InvalidSocialMediaPlatformException e, HttpServletRequest request) {
        log.warn("InvalidSocialMediaPlatformException: {}", e.getMessage(), e);
        InfluencerCreateApiErrorCode apiErrorCode = InfluencerCreateApiErrorCode.INVALID_SOCIAL_MEDIA_TYPE;
        return createResponseEntityFromApiErrorCode(request, apiErrorCode);
    }

    @ExceptionHandler(InfluencerNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handlerInfluencerNotFoundException(InfluencerNotFoundException e, HttpServletRequest request) {
        log.warn("InfluencerNotFoundException: {}", e.getMessage(), e);
        InfluencerReadApiErrorCode apiErrorCode = InfluencerReadApiErrorCode.INFLUENCER_NOT_FOUND;
        return createResponseEntityFromApiErrorCode(request, apiErrorCode);
    }

    @ExceptionHandler(InvalidFileRequestException.class)
    public ResponseEntity<ApiErrorResponse> handlerInvalidFileRequestException(InvalidFileRequestException e, HttpServletRequest request) {
        log.warn("InvalidFileRequestException: {}", e.getMessage(), e);
        InfluencerCreateApiErrorCode apiErrorCode = InfluencerCreateApiErrorCode.INVALID_PROFILE_IMAGE_REQUEST;
        return createResponseEntityFromApiErrorCode(request, apiErrorCode);
    }

    @ExceptionHandler(ThirdPartyStorageException.class)
    public ResponseEntity<ApiErrorResponse> handlerThirdPartyStorageException(ThirdPartyStorageException e, HttpServletRequest request) {
        log.warn("ThirdPartyStorageException: {}", e.getMessage(), e);
        InfluencerCreateApiErrorCode apiErrorCode = InfluencerCreateApiErrorCode.PROFILE_IMAGE_UPLOAD_FAILED;
        return createResponseEntityFromApiErrorCode(request, apiErrorCode);
    }
}
