package com.leesh.inflpick.influencer.adapter.in.web.value;

import com.leesh.inflpick.v2.shared.adapter.in.web.ApiErrorCode;
import com.leesh.inflpick.influencer.core.domain.exception.*;
import com.leesh.inflpick.v2.influencer.domain.exception.InvalidInfluencerDescriptionException;
import com.leesh.inflpick.v2.influencer.domain.exception.InvalidInfluencerNameFormatException;
import org.springframework.http.HttpStatus;

public enum InfluencerCreateApiErrorCode implements ApiErrorCode {

    INFLUENCER_NAME_VALIDATION_FAILED("IN_C_0001", HttpStatus.BAD_REQUEST, "유효한 인플루언서 이름을 입력해주세요.", InvalidInfluencerNameFormatException.ERROR_MESSAGE_FORMAT.toPattern(),  "인플루언서 이름 필드가 유효성 검증에 실패한 경우에 발생합니다."),
    INFLUENCER_INTRODUCTION_VALIDATION_FAILED("IN_C_0002", HttpStatus.BAD_REQUEST, "유효한 인플루언서 소개를 입력해주세요.", InfluencerIntroductionValidationFailedException.ERROR_MESSAGE_FORMAT.toPattern(), "인플루언서 소개 필드가 유효성 검증에 실패한 경우에 발생합니다."),
    INFLUENCER_DESCRIPTION_VALIDATION_FAILED("IN_C_0003", HttpStatus.BAD_REQUEST, "유효한 인플루언서 설명을 입력해주세요.", InvalidInfluencerDescriptionException.ERROR_MESSAGE_FORMAT.toPattern(), "인플루언서 설명 필드가 유효성 검증에 실패한 경우에 발생합니다."),
    INVALID_SOCIAL_MEDIA_TYPE("IN_C_0004", HttpStatus.BAD_REQUEST, "유효하지 않은 소셜 미디어 타입이에요.", InvalidSocialMediaPlatformException.ERROR_MESSAGE_FORMAT.toPattern(), "소셜 미디어 타입이 유효하지 않은 경우에 발생합니다."),
    KEYWORD_MAXIMUM_SIZE_EXCEED("IN_C_0005", HttpStatus.BAD_REQUEST, "최대 키워드 수를 초과했어요.", KeywordMaximumSizeExceedException.ERROR_MESSAGE_FORMAT.toPattern(), "키워드 등록 요청이 최대 허용 개수를 초과한 경우에 발생합니다."),
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
    public String getCode() {
        return this.code;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    @Override
    public String getReason() {
        return this.reason;
    }

    @Override
    public String getAction() {
        return this.action;
    }

    @Override
    public String getComment() {
        return this.comment;
    }
}
