package com.leesh.inflpick.influencer.adapter.in.web;

import com.leesh.inflpick.common.adapter.in.web.ApiErrorCode;
import com.leesh.inflpick.common.adapter.out.persistence.ResourceNotFoundException;
import org.springframework.http.HttpStatus;

public enum InfluencerReadApiErrorCode implements ApiErrorCode {

    INFLUENCER_NOT_FOUND("IN_R_0001", HttpStatus.NOT_FOUND, "UUID에 해당하는 인플루언서를 찾을 수 없습니다.", ResourceNotFoundException.MESSAGE_FORMAT.toPattern(), "UUID에 해당하는 인플루언서를 찾을 수 없는 경우에 발생합니다."),
    ;

    private final String code;
    private final HttpStatus httpStatus;
    private final String reason;
    private final String action;
    private final String comment;

    InfluencerReadApiErrorCode(String code, HttpStatus httpStatus, String reason, String action, String comment) {
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
