package com.leesh.inflpick.v2.influencer.adapter.in.web.controller.constant;

import com.leesh.inflpick.v2.shared.adapter.in.web.ApiErrorCode;
import org.springframework.http.HttpStatus;

public enum UpdateProfileImageApiErrorCode implements ApiErrorCode {

    INVALID_PROFILE_IMAGE_REQUEST("UPI_0001", HttpStatus.BAD_REQUEST, "이미지 파일이 아니에요.", "이미지 파일만 업로드 할 수 있어요.", "주로 파일의 이름이 비어있거나, 파일 내용에 잘못된 문자 형식으로 인해 발생합니다."),
    ;

    private final String code;
    private final HttpStatus httpStatus;
    private final String reason;
    private final String action;
    private final String comment;

    UpdateProfileImageApiErrorCode(String code, HttpStatus httpStatus, String reason, String action, String comment) {
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
