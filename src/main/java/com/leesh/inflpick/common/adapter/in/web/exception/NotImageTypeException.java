package com.leesh.inflpick.common.adapter.in.web.exception;

import com.leesh.inflpick.common.domain.ErrorCode;
import org.springframework.http.HttpStatus;

public class NotImageTypeException extends RuntimeException implements ErrorCode {

    public NotImageTypeException() {
        super("이미지 파일이 아니에요.");
    }

    @Override
    public String getCode() {
        return "NOT_IMAGE_TYPE";
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
        return "이미지 파일을 확인 후 다시 요청해주세요.";
    }

    @Override
    public String getComment() {
        return "이미지 파일이 아닌 파일을 업로드한 경우에 발생합니다.";
    }
}
