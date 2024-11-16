package com.leesh.inflpick.v2.token.application.port.in.exception;

import com.leesh.inflpick.v2.common.domain.ErrorCode;
import org.springframework.http.HttpStatus;

public class ExpiredRefreshTokenException extends RuntimeException implements ErrorCode {
    public ExpiredRefreshTokenException() {
        super("Expired refresh token");
    }

    @Override
    public String getCode() {
        return "EXPIRED_REFRESH_TOKEN";
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }

    @Override
    public String getReason() {
        return "로그인이 만료되었어요.";
    }

    @Override
    public String getAction() {
        return "다시 로그인 해주세요.";
    }

    @Override
    public String getComment() {
        return "갱신 토큰이 만료되어 토큰을 발급할 수 없을 때 발생합니다.";
    }
}
