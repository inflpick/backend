package com.leesh.inflpick.user.adapter.in.web;

import com.leesh.inflpick.common.adapter.in.web.value.ApiErrorCode;
import com.leesh.inflpick.user.port.out.NotSupportedOauth2TypeException;
import org.springframework.http.HttpStatus;

public enum Oauth2LoginApiErrorCode implements ApiErrorCode {

    NOT_SUPPORTED_OAUTH2_TYPE("AU_O_0001", HttpStatus.BAD_REQUEST, "지원하지 않는 OAuth2.0 타입입니다.", NotSupportedOauth2TypeException.ERROR_MESSAGE_FORMAT.toPattern(), "지원하지 않는 OAuth2.0 타입이 요청된 경우에 발생합니다.");

    private final String code;
    private final HttpStatus httpStatus;
    private final String reason;
    private final String action;
    private final String comment;

    Oauth2LoginApiErrorCode(String code, HttpStatus httpStatus, String reason, String action, String comment) {
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
