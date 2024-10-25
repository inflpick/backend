package com.leesh.inflpick.v2.influencer.adapter.in.web.dto;

import com.leesh.inflpick.v2.shared.adapter.in.web.ApiErrorCode;
import org.springframework.http.HttpStatus;

public enum UpdateProfileImageApiErrorCode implements ApiErrorCode {

    INVALID_PROFILE_IMAGE_REQUEST("IN-U-0001", HttpStatus.BAD_REQUEST, "이미지 파일이 아니에요.", "이미지 파일만 업로드 할 수 있어요.", "주로 파일의 이름이 비어있거나, 파일 내용에 잘못된 문자 형식으로 인해 발생합니다."),
    PROFILE_IMAGE_UPLOAD_FAILED("IN-U-0002", HttpStatus.SERVICE_UNAVAILABLE, "외부 서비스의 오류로 인해 프로필 이미지 파일 업로드에 실패하였습니다.", "잠시 후 다시 요청하거나, 인플픽 관리자(info@inflpick.com)에게 문의 바랍니다.", "프로필 이미지 파일 업로드에 실패한 경우에 발생합니다."),
    INFLUENCER_NOT_FOUND("IN-U-0003", HttpStatus.NOT_FOUND, "인플루언서를 찾을 수 없어요.", "인플루언서가 존재하지 않아 요청을 처리할 수 없습니다.", "ID에 해당하는 인플루언서를 찾을 수 없는 경우에 발생합니다."),
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
