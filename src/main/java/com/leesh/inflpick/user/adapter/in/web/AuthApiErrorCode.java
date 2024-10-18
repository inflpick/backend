package com.leesh.inflpick.user.adapter.in.web;

import com.leesh.inflpick.common.adapter.in.web.value.ApiErrorCode;
import org.springframework.http.HttpStatus;

public enum AuthApiErrorCode implements ApiErrorCode {

    INVALID_AUTHENTICATION_CODE("U_0001", HttpStatus.BAD_REQUEST, "인증되지 않았어요.", "다시 로그인 하거나, 문제가 반복되는 경우 인플픽 관리자(info@inflpick.com)에게 문의 바랍니다.", "인증 코드에 해당하는 유저를 찾을 수 없는 경우 발생합니다."),
    WRONG_GRANT_TYPE("U_0002", HttpStatus.BAD_REQUEST, "잘못된 인증 방식입니다.", "인증 방식을 확인해주세요.", "인증 방식이 잘못된 경우 발생합니다.")
    ;

    private final String code;
    private final HttpStatus httpStatus;
    private final String reason;
    private final String action;
    private final String comment;

    AuthApiErrorCode(String code, HttpStatus httpStatus, String reason, String action, String comment) {
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
