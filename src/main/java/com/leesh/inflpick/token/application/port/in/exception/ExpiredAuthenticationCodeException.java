package com.leesh.inflpick.token.application.port.in.exception;

import com.leesh.inflpick.common.domain.ErrorCode;
import org.springframework.http.HttpStatus;

public class ExpiredAuthenticationCodeException extends RuntimeException implements ErrorCode {
    public ExpiredAuthenticationCodeException() {
        super("Expired authentication code");
    }

    @Override
    public String getCode() {
        return "EXPIRED_AUTHENTICATION_CODE";
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }

    @Override
    public String getReason() {
        return "인증 요청이 만료됐어요.";
    }

    @Override
    public String getAction() {
        return "재 로그인이 필요해요.";
    }

    @Override
    public String getComment() {
        return "이미 토큰을 발급 받은 인증 코드로 다시 토큰 발급 요청을 하는 경우 발생합니다.";
    }
}
