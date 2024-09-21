package com.leesh.inflpick.influencer.adapter.in.web;

import com.leesh.inflpick.common.adapter.in.web.ApiErrorCode;
import com.leesh.inflpick.influencer.core.domain.*;
import org.springframework.http.HttpStatus;

public enum InfluencerCreateApiErrorCode implements ApiErrorCode {

    INFLUENCER_NAME_VALIDATION_FAILED("IN_C_0001", HttpStatus.BAD_REQUEST, "입력한 인플루언서 이름이 유효성 검증에 실패하였습니다.", InfluencerNameValidationFailedException.ERROR_MESSAGE_FORMAT.toPattern(),  "인플루언서 이름 필드가 유효성 검증에 실패한 경우에 발생합니다."),
    INFLUENCER_INTRODUCTION_VALIDATION_FAILED("IN_C_0002", HttpStatus.BAD_REQUEST, "입력한 인플루언서 소개가 유효성 검증에 실패하였습니다.", InfluencerIntroductionValidationFailedException.ERROR_MESSAGE_FORMAT.toPattern(), "인플루언서 소개 필드가 유효성 검증에 실패한 경우에 발생합니다."),
    INFLUENCER_DESCRIPTION_VALIDATION_FAILED("IN_C_0003", HttpStatus.BAD_REQUEST, "입력한 인플루언서 설명이 유효성 검증에 실패하였습니다.", InfluencerDescriptionValidationFailedException.ERROR_MESSAGE_FORMAT.toPattern(), "인플루언서 설명 필드가 유효성 검증에 실패한 경우에 발생합니다."),
    PROFILE_IMAGE_URI_SYNTAX_ERROR("IN_C_0004", HttpStatus.BAD_REQUEST, "프로필 이미지 URI 형식이 올바르지 않습니다.", ProfileImageUriSyntaxException.ERROR_MESSAGE_FORMAT.toPattern(), "프로필 이미지 URI 형식이 올바르지 않은 경우에 발생합니다."),
    SOCIAL_MEDIA_PROFILE_LINK_URI_SYNTAX_ERROR("IN_C_0005", HttpStatus.BAD_REQUEST, "소셜 미디어 프로필 링크 URI 형식이 올바르지 않습니다.", SocialMediaProfileLinkUriSyntaxException.ERROR_MESSAGE_FORMAT.toPattern(), "소셜 미디어 프로필 링크 URI 형식이 올바르지 않은 경우에 발생합니다."),
    INVALID_SOCIAL_MEDIA_TYPE("IN_C_0006", HttpStatus.BAD_REQUEST, "유효하지 않은 소셜 미디어 타입입니다.", InvalidSocialMediaPlatformException.ERROR_MESSAGE_FORMAT.toPattern(), "소셜 미디어 타입이 유효하지 않은 경우에 발생합니다."),
    KEYWORD_MAXIMUM_SIZE_EXCEED("IN_C_0007", HttpStatus.BAD_REQUEST, "등록 가능한 최대 키워드를 초과하였습니다.", KeywordMaximumSizeExceedException.ERROR_MESSAGE_FORMAT.toPattern(), "키워드 등록 요청이 최대 허용 개수를 초과한 경우에 발생합니다."),
    PROFILE_IMAGE_NOT_EXIST("IN_C_0008", HttpStatus.BAD_REQUEST, "API 요청에 프로필 이미지가 존재하지 않습니다.", ProfileImageNotExistException.ERROR_MESSAGE_FORMAT.toPattern(), "프로필 이미지가 존재하지 않는 경우에 발생합니다. API 요청에 프로필 이미지를 포함하여 재요청 해주세요."),
    ;

    private final String code;
    private final HttpStatus httpStatus;
    private final String reason;
    private final String action;
    private final String comment;

    InfluencerCreateApiErrorCode(String code, HttpStatus httpStatus, String reason, String action, String comment) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.reason = reason;
        this.action = action;
        this.comment = comment;
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

    @Override
    public String comment() {
        return this.comment;
    }
}
