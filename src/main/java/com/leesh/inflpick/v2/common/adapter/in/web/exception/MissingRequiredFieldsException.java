package com.leesh.inflpick.v2.common.adapter.in.web.exception;

import com.leesh.inflpick.v2.common.domain.ErrorCode;
import org.springframework.http.HttpStatus;

public class MissingRequiredFieldsException extends RuntimeException implements ErrorCode {

    public MissingRequiredFieldsException() {
        super("필수 값이 입력되지 않았어요.");
    }

    @Override
    public String getCode() {
        return "MISSING_REQUIRED_FIELDS";
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }

    @Override
    public String getReason() {
        return "필수 값이 입력되지 않았어요.";
    }

    @Override
    public String getAction() {
        return "필수 값을 입력 후 다시 요청해주세요.";
    }

    @Override
    public String getComment() {
        return "API 요청 시, 필수 입력 값이 입력되지 않은 경우에 발생합니다.";
    }
}
