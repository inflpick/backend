package com.leesh.inflpick.v2.influencer.adapter.in.web;

import com.leesh.inflpick.v2.influencer.domain.vo.SnsPlatform;
import com.leesh.inflpick.v2.shared.adapter.in.web.ApiErrorCode;
import org.springframework.http.HttpStatus;

public enum CommandInfluencerApiErrorCode implements ApiErrorCode {

    INFLUENCER_NAME_VALIDATION_FAILED("IN-C-0001", HttpStatus.BAD_REQUEST, "유효한 인플루언서 이름을 입력해주세요.", "인플루언서 이름은 1-300자 사이로 입력할 수 있어요.",  "인플루언서 이름 필드가 유효성 검증에 실패한 경우에 발생합니다."),
    INFLUENCER_INTRODUCTION_VALIDATION_FAILED("IN-C-0002", HttpStatus.BAD_REQUEST, "유효한 인플루언서 소개를 입력해주세요.", "인플루언서 소개는 1000자 이내로 입력할 수 있어요.", "인플루언서 소개 필드가 유효성 검증에 실패한 경우에 발생합니다."),
    INFLUENCER_DESCRIPTION_VALIDATION_FAILED("IN-C-0003", HttpStatus.BAD_REQUEST, "유효한 인플루언서 설명을 입력해주세요.", "인플루언서 설명은 1-50000자 사이로 입력할 수 있어요.", "인플루언서 설명 필드가 유효성 검증에 실패한 경우에 발생합니다."),
    INVALID_SOCIAL_MEDIA_TYPE("IN-C-0004", HttpStatus.BAD_REQUEST, "지원하지 않는 SNS 플랫폼 타입이에요.", "SNS 플랫폼은 [" + SnsPlatform.availableValues() + "] 만 입력할 수 있어요.", "SNS 플랫폼 타입이 유효하지 않은 경우에 발생합니다."),
    KEYWORD_MAXIMUM_SIZE_EXCEED("IN-C-0005", HttpStatus.BAD_REQUEST, "최대 키워드 수를 초과했어요.", "키워드는 10개까지만 등록 가능해요.", "키워드 등록 요청이 최대 허용 개수를 초과한 경우에 발생합니다."),
    INFLUENCER_NOT_FOUND("IN-C-0006", HttpStatus.NOT_FOUND, "인플루언서를 찾을 수 없어요.", "인플루언서가 존재하지 않아 요청을 처리할 수 없습니다.", "ID에 해당하는 인플루언서를 찾을 수 없는 경우에 발생합니다."),
    ;

    private final String code;
    private final HttpStatus httpStatus;
    private final String reason;
    private final String action;
    private final String comment;

    CommandInfluencerApiErrorCode(String code, HttpStatus httpStatus, String reason, String action, String comment) {
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
