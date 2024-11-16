package com.leesh.inflpick.common.adapter.in.web.filter.security;

import com.leesh.inflpick.common.domain.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

public class InvalidAuthenticationException extends AuthenticationException implements ErrorCode {
    public InvalidAuthenticationException() {
        super("Invalid accessToken");
    }

    @Override
    public String getCode() {
        return "INVALID_AUTHENTICATION";
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.UNAUTHORIZED;
    }

    @Override
    public String getReason() {
        return "토큰이 올바르지 않아요.";
    }

    @Override
    public String getAction() {
        return "다시 로그인 후, 토큰을 재발급 받아주세요.";
    }

    @Override
    public String getComment() {
        return "토큰이 올바르지 않은 경우에 발생합니다.";
    }
}
