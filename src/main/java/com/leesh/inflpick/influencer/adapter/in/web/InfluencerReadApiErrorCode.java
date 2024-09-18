package com.leesh.inflpick.influencer.adapter.in.web;

import com.leesh.inflpick.common.adapter.in.web.ApiErrorCode;
import org.springframework.http.HttpStatus;

public enum InfluencerReadApiErrorCode implements ApiErrorCode {

    INFLUENCER_NOT_FOUND("IN_R_0001", HttpStatus.NOT_FOUND, "ID에 해당하는 인플루언서를 찾을 수 없습니다.", "ID를 확인하고 다시 시도해주세요."),
    ;

    private final String code;
    private final HttpStatus httpStatus;
    private final String reason;
    private final String action;

    InfluencerReadApiErrorCode(String code, HttpStatus httpStatus, String reason, String action) {
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
