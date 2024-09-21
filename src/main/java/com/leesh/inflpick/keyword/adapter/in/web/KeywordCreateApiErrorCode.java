package com.leesh.inflpick.keyword.adapter.in.web;

import com.leesh.inflpick.common.adapter.in.web.value.ApiErrorCode;
import com.leesh.inflpick.keyword.core.domain.KeywordColor;
import com.leesh.inflpick.keyword.core.domain.KeywordName;
import org.springframework.http.HttpStatus;

public enum KeywordCreateApiErrorCode implements ApiErrorCode {

    KEYWORD_NAME_VALIDATE_FAILED("K_C_0001", HttpStatus.BAD_REQUEST, "입력한 키워드 이름이 유효성 검증에 실패하였습니다.", KeywordName.ERROR_MESSAGE_FORMAT.toPattern(), "키워드 이름 필드가 유효성 검증에 실패한 경우에 발생합니다."),
    KEYWORD_COLOR_VALIDATION_FAILED("K_C_0002", HttpStatus.BAD_REQUEST, "입력한 키워드 색상이 유효성 검증에 실패하였습니다.", KeywordColor.ERROR_MESSAGE_FORMAT.toPattern(), "키워드 색상 필드가 유효성 검증에 실패한 경우에 발생합니다."),
    DUPLICATE_KEYWORD_NAME_EXCEPTION("K_C_0003", HttpStatus.BAD_REQUEST, "이미 존재하는 키워드 명입니다.", "다른 키워드 명을 사용해주세요.", "이미 존재하는 키워드 명을 생성하는 경우에 발생합니다."),
    ;

    private final String code;
    private final HttpStatus httpStatus;
    private final String reason;
    private final String action;
    private final String comment;

    KeywordCreateApiErrorCode(String code, HttpStatus httpStatus, String reason, String action, String comment) {
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
