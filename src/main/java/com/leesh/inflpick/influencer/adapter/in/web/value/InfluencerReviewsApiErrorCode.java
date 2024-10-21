package com.leesh.inflpick.influencer.adapter.in.web.value;

import com.leesh.inflpick.v2.adapter.in.web.common.error.ApiErrorCode;
import com.leesh.inflpick.review.core.domain.ReviewContentsValidationFailedException;
import com.leesh.inflpick.review.core.domain.ReviewUriValidationFailedException;
import org.springframework.http.HttpStatus;

public enum InfluencerReviewsApiErrorCode implements ApiErrorCode {

    REVIEW_CONTENTS_VALIDATION_FAILED("IN_V_0001", HttpStatus.BAD_REQUEST, "유효한 리뷰 내용을 입력해주세요.", ReviewContentsValidationFailedException.ERROR_MESSAGE_FORMAT.toPattern(), "리뷰 내용이 유효하지 않은 경우에 발생합니다."),
    REVIEW_URI_VALIDATION_FAILED("IN_V_0002", HttpStatus.BAD_REQUEST, "유효한 리뷰 URI를 입력해주세요.", ReviewUriValidationFailedException.ERROR_MESSAGE_FORMAT.toPattern(), "리뷰 URI가 유효하지 않은 경우에 발생합니다.");

    private final String code;
    private final HttpStatus httpStatus;
    private final String reason;
    private final String action;
    private final String comment;

    InfluencerReviewsApiErrorCode(String code, HttpStatus httpStatus, String reason, String action, String comment) {
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
