package com.leesh.inflpick.influencer.adapter.in.web.value;

import com.leesh.inflpick.common.adapter.in.web.value.ApiErrorCode;
import org.springframework.http.HttpStatus;

public enum InfluencerReviewsApiErrorCode implements ApiErrorCode {

    INVALID_PROFILE_IMAGE_REQUEST("IN_C_0006", HttpStatus.BAD_REQUEST, "잘못된 이미지 파일이에요.", "업로드한 이미지 파일의 이름이나, 내용을 확인 후 올바른 파일로 재요청 바랍니다.", "주로 파일의 이름이 비어있거나, 파일 내용에 잘못된 문자 형식으로 인해 발생합니다."),
    PROFILE_IMAGE_UPLOAD_FAILED("IN_C_0007", HttpStatus.INTERNAL_SERVER_ERROR, "외부 서비스의 오류로 인해 프로필 이미지 파일 업로드에 실패하였습니다.", "잠시 후 다시 요청하거나, 인플픽 관리자에게 문의 바랍니다.", "프로필 이미지 파일 업로드에 실패한 경우에 발생합니다."),
    ;

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
