package com.leesh.inflpick.influencer.adapter.in.web;

import com.leesh.inflpick.common.adapter.in.web.ApiErrorCode;
import com.leesh.inflpick.influencer.core.domain.*;
import org.springframework.http.HttpStatus;

public enum InfluencerCreateApiErrorCode implements ApiErrorCode {

    INFLUENCER_NAME_VALIDATION_FAILED("IN_C_0001", HttpStatus.BAD_REQUEST, "입력한 인플루언서 이름이 유효성 검증에 실패하였습니다.", InfluencerName.ERROR_MESSAGE_FORMAT.toPattern()),
    INFLUENCER_INTRODUCTION_VALIDATION_FAILED("IN_C_0002", HttpStatus.BAD_REQUEST, "입력한 인플루언서 소개가 유효성 검증에 실패하였습니다.", InfluencerIntroduction.ERROR_MESSAGE_FORMAT.toPattern()),
    INFLUENCER_DESCRIPTION_VALIDATION_FAILED("IN_C_0003", HttpStatus.BAD_REQUEST, "입력한 인플루언서 설명이 유효성 검증에 실패하였습니다.", InfluencerDescription.ERROR_MESSAGE_FORMAT.toPattern()),
    PROFILE_IMAGE_URI_SYNTAX_ERROR("IN_C_0004", HttpStatus.BAD_REQUEST, "프로필 이미지 URI 형식이 올바르지 않습니다.", ProfileImage.ERROR_MESSAGE_FORMAT.toPattern()),
    SOCIAL_MEDIA_PROFILE_LINK_URI_SYNTAX_ERROR("IN_C_0005", HttpStatus.BAD_REQUEST, "소셜 미디어 프로필 링크 URI 형식이 올바르지 않습니다.", SocialMediaProfileLink.ERROR_MESSAGE_FORMAT.toPattern()),
    INVALID_SOCIAL_MEDIA_TYPE("IN_C_0006", HttpStatus.BAD_REQUEST, "유효하지 않은 소셜 미디어 타입입니다.", "소셜 미디어 타입은 %s 중 하나여야 합니다.".formatted(String.join(", ", SocialMediaPlatform.availablePlatforms()))),
    ;

    private final String code;
    private final HttpStatus httpStatus;
    private final String reason;
    private final String action;

    InfluencerCreateApiErrorCode(String code, HttpStatus httpStatus, String reason, String action) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.reason = reason;
        this.action = action;
    }

    @Override
    public String code() {
        return this.code;
    }

    @Override
    public HttpStatus httpStatus() {
        return this.httpStatus;
    }

    @Override
    public String reason() {
        return this.reason;
    }

    @Override
    public String action() {
        return this.action;
    }
}
