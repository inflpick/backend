package com.leesh.inflpick.influencer.adapter.in.web;

import com.leesh.inflpick.common.adapter.in.web.ApiErrorResponse;
import com.leesh.inflpick.influencer.adapter.out.persistence.InfluencerNotFoundException;
import com.leesh.inflpick.influencer.core.domain.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.leesh.inflpick.common.adapter.in.web.CommonExceptionControllerAdvice.createResponseEntityFromApiErrorCode;

@Slf4j
@RestControllerAdvice
public class InfluencerExceptionControllerAdvice {

    @ExceptionHandler(InfluencerNameValidationFailedException.class)
    public ResponseEntity<ApiErrorResponse> handlerInfluencerNameValidationFailedException(InfluencerNameValidationFailedException e, HttpServletRequest request) {
        log.warn("InfluencerNameValidationFailedException: {}", e.getMessage(), e.getCause());
        InfluencerCreateApiErrorCode apiErrorCode = InfluencerCreateApiErrorCode.INFLUENCER_NAME_VALIDATION_FAILED;
        return createResponseEntityFromApiErrorCode(request, apiErrorCode);
    }

    @ExceptionHandler(InfluencerIntroductionValidationFailedException.class)
    public ResponseEntity<ApiErrorResponse> handlerInfluencerIntroductionValidationFailedException(InfluencerIntroductionValidationFailedException e, HttpServletRequest request) {
        log.warn("InfluencerIntroductionValidationFailedException: {}", e.getMessage(), e.getCause());
        InfluencerCreateApiErrorCode apiErrorCode = InfluencerCreateApiErrorCode.INFLUENCER_INTRODUCTION_VALIDATION_FAILED;
        return createResponseEntityFromApiErrorCode(request, apiErrorCode);
    }

    @ExceptionHandler(InfluencerDescriptionValidationFailedException.class)
    public ResponseEntity<ApiErrorResponse> handlerInfluencerDescriptionValidationFailedException(InfluencerDescriptionValidationFailedException e, HttpServletRequest request) {
        log.warn("InfluencerDescriptionValidationFailedException: {}", e.getMessage(), e.getCause());
        InfluencerCreateApiErrorCode apiErrorCode = InfluencerCreateApiErrorCode.INFLUENCER_DESCRIPTION_VALIDATION_FAILED;
        return createResponseEntityFromApiErrorCode(request, apiErrorCode);
    }

    @ExceptionHandler(ProfileImageUriSyntaxException.class)
    public ResponseEntity<ApiErrorResponse> handlerProfileImageUriSyntaxException(ProfileImageUriSyntaxException e, HttpServletRequest request) {
        log.warn("ProfileImageUriSyntaxException: {}", e.getMessage(), e.getCause());
        InfluencerCreateApiErrorCode apiErrorCode = InfluencerCreateApiErrorCode.PROFILE_IMAGE_URI_SYNTAX_ERROR;
        return createResponseEntityFromApiErrorCode(request, apiErrorCode);
    }

    @ExceptionHandler(InvalidSocialMediaPlatformException.class)
    public ResponseEntity<ApiErrorResponse> handlerInvalidSocialMediaPlatformException(InvalidSocialMediaPlatformException e, HttpServletRequest request) {
        log.warn("InvalidSocialMediaPlatformException: {}", e.getMessage(), e.getCause());
        InfluencerCreateApiErrorCode apiErrorCode = InfluencerCreateApiErrorCode.INVALID_SOCIAL_MEDIA_TYPE;
        return createResponseEntityFromApiErrorCode(request, apiErrorCode);
    }

    @ExceptionHandler(SocialMediaProfileLinkUriSyntaxException.class)
    public ResponseEntity<ApiErrorResponse> handlerSocialMediaProfileLinkUriSyntaxException(SocialMediaProfileLinkUriSyntaxException e, HttpServletRequest request) {
        log.warn("SocialMediaProfileLinkUriSyntaxException: {}", e.getMessage(), e.getCause());
        InfluencerCreateApiErrorCode apiErrorCode = InfluencerCreateApiErrorCode.SOCIAL_MEDIA_PROFILE_LINK_URI_SYNTAX_ERROR;
        return createResponseEntityFromApiErrorCode(request, apiErrorCode);
    }

    @ExceptionHandler(InfluencerNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handlerInfluencerNotFoundException(InfluencerNotFoundException e, HttpServletRequest request) {
        log.warn("InfluencerNotFoundException: {}", e.getMessage(), e.getCause());
        InfluencerReadApiErrorCode apiErrorCode = InfluencerReadApiErrorCode.INFLUENCER_NOT_FOUND;
        return createResponseEntityFromApiErrorCode(request, apiErrorCode);
    }
}
