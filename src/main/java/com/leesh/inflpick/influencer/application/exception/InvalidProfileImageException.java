package com.leesh.inflpick.influencer.application.exception;

import com.leesh.inflpick.common.domain.ErrorCode;
import org.springframework.http.HttpStatus;

public class InvalidProfileImageException extends RuntimeException implements ErrorCode {

    public InvalidProfileImageException() {
        super("Profile image format is not supported");
    }

    public InvalidProfileImageException(String message) {
        super("Profile image format is not supported, file: %s".formatted(message));
    }

    @Override
    public String getCode() {
        return "INVALID_PROFILE_IMAGE_REQUEST";
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }

    @Override
    public String getReason() {
        return "이미지 파일이 아니에요.";
    }

    @Override
    public String getAction() {
        return "이미지 파일만 업로드 할 수 있어요.";
    }

    @Override
    public String getComment() {
        return "주로 파일의 이름이 비어있거나, 파일 내용에 잘못된 문자 형식으로 인해 발생합니다.";
    }
}
