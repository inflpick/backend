package com.leesh.inflpick.v2.influencer.adapter.in.web.controller;

import com.leesh.inflpick.v2.influencer.adapter.in.web.dto.CommandInfluencerApiErrorCode;
import com.leesh.inflpick.v2.influencer.adapter.in.web.dto.UpdateProfileImageApiErrorCode;
import com.leesh.inflpick.v2.influencer.application.exception.InfluencerNotFoundException;
import com.leesh.inflpick.v2.influencer.application.exception.ProfileImageFormatException;
import com.leesh.inflpick.v2.influencer.domain.exception.*;
import com.leesh.inflpick.v2.shared.adapter.in.web.ApiErrorResponse;
import com.leesh.inflpick.v2.shared.application.exception.ThirdPartyStorageException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.leesh.inflpick.v2.shared.adapter.in.web.CommonExceptionHandler.createResponseEntityFromApiErrorCode;

@Slf4j
@RestControllerAdvice
public class InfluencerExceptionHandler {

    @ExceptionHandler(InfluencerNameFormatException.class)
    public ResponseEntity<ApiErrorResponse> handleInfluencerNameFormatException(HttpServletRequest request, InfluencerNameFormatException e) {
        log.error("InfluencerNameFormatException occurred: {}", e.getMessage());
        return createResponseEntityFromApiErrorCode(request, CommandInfluencerApiErrorCode.INFLUENCER_NAME_VALIDATION_FAILED);
    }

    @ExceptionHandler(InfluencerIntroductionFormatException.class)
    public ResponseEntity<ApiErrorResponse> handleInfluencerIntroductionFormatException(HttpServletRequest request, InfluencerIntroductionFormatException e) {
        log.error("InfluencerIntroductionFormatException occurred: {}", e.getMessage());
        return createResponseEntityFromApiErrorCode(request, CommandInfluencerApiErrorCode.INFLUENCER_INTRODUCTION_VALIDATION_FAILED);
    }

    @ExceptionHandler(InfluencerDescriptionFormatException.class)
    public ResponseEntity<ApiErrorResponse> handleInfluencerDescriptionFormatException(HttpServletRequest request, InfluencerDescriptionFormatException e) {
        log.error("InfluencerDescriptionFormatException occurred: {}", e.getMessage());
        return createResponseEntityFromApiErrorCode(request, CommandInfluencerApiErrorCode.INFLUENCER_DESCRIPTION_VALIDATION_FAILED);
    }

    @ExceptionHandler(NotSupportSnsPlatformException.class)
    public ResponseEntity<ApiErrorResponse> handleSnsPlatformValueException(HttpServletRequest request, NotSupportSnsPlatformException e) {
        log.error("SnsPlatformValueException occurred: {}", e.getMessage());
        return createResponseEntityFromApiErrorCode(request, CommandInfluencerApiErrorCode.INVALID_SOCIAL_MEDIA_TYPE);
    }

    @ExceptionHandler(MaximumInfluencerKeywordSizeException.class)
    public ResponseEntity<ApiErrorResponse> handleMaximumInfluencerKeywordSizeException(HttpServletRequest request, MaximumInfluencerKeywordSizeException e) {
        log.error("MaximumInfluencerKeywordSizeException occurred: {}", e.getMessage());
        return createResponseEntityFromApiErrorCode(request, CommandInfluencerApiErrorCode.KEYWORD_MAXIMUM_SIZE_EXCEED);
    }

    @ExceptionHandler(InfluencerNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleInfluencerNotFoundException(HttpServletRequest request, InfluencerNotFoundException e) {
        log.error("InfluencerNotFoundException occurred: {}", e.getMessage());
        return createResponseEntityFromApiErrorCode(request, CommandInfluencerApiErrorCode.INFLUENCER_NOT_FOUND);
    }

    @ExceptionHandler(ProfileImageFormatException.class)
    public ResponseEntity<ApiErrorResponse> handlerInvalidFileRequestException(ProfileImageFormatException e, HttpServletRequest request) {
        log.warn("InvalidFileRequestException: {}", e.getMessage(), e);
        UpdateProfileImageApiErrorCode apiErrorCode = UpdateProfileImageApiErrorCode.INVALID_PROFILE_IMAGE_REQUEST;
        return createResponseEntityFromApiErrorCode(request, apiErrorCode);
    }

    @ExceptionHandler(ThirdPartyStorageException.class)
    public ResponseEntity<ApiErrorResponse> handlerThirdPartyStorageException(ThirdPartyStorageException e, HttpServletRequest request) {
        log.warn("ThirdPartyStorageException: {}", e.getMessage(), e);
        UpdateProfileImageApiErrorCode apiErrorCode = UpdateProfileImageApiErrorCode.PROFILE_IMAGE_UPLOAD_FAILED;
        return createResponseEntityFromApiErrorCode(request, apiErrorCode);
    }

}
