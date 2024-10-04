package com.leesh.inflpick.review.adapter.in.web;

import com.leesh.inflpick.common.adapter.in.web.value.ApiErrorCode;
import com.leesh.inflpick.review.core.domain.ReviewContentsValidationFailedException;
import com.leesh.inflpick.review.core.domain.ReviewUriValidationFailedException;
import org.springframework.http.HttpStatus;

public enum ReviewCreateApiErrorCode implements ApiErrorCode {

    REVIEW_CONTENTS_VALIDATION_FAILED("IN_V_0001", HttpStatus.BAD_REQUEST, "유효한 리뷰 내용을 입력해주세요.", ReviewContentsValidationFailedException.ERROR_MESSAGE_FORMAT.toPattern(), "리뷰 내용이 유효하지 않은 경우에 발생합니다."),
    REVIEW_URI_VALIDATION_FAILED("IN_V_0002", HttpStatus.BAD_REQUEST, "유효한 리뷰 URI를 입력해주세요.", ReviewUriValidationFailedException.ERROR_MESSAGE_FORMAT.toPattern(), "리뷰 URI가 유효하지 않은 경우에 발생합니다."),
    ;

    private final String code;
    private final HttpStatus httpStatus;
    private final String reason;
    private final String action;
    private final String comment;

    ReviewCreateApiErrorCode(String code, HttpStatus httpStatus, String reason, String action, String comment) {
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
