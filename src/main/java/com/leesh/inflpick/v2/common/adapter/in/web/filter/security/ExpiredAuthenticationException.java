package com.leesh.inflpick.v2.common.adapter.in.web.filter.security;

import com.leesh.inflpick.v2.common.domain.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

public class ExpiredAuthenticationException extends AuthenticationException implements ErrorCode {
    public ExpiredAuthenticationException() {
        super("AccessToken is expired");
    }

    @Override
    public String getCode() {
        return "EXPIRED_AUTHENTICATION";
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.UNAUTHORIZED;
    }

    @Override
    public String getReason() {
        return "토큰이 만료되었어요.";
    }

    @Override
    public String getAction() {
        return "다시 로그인 후, 토큰을 재발급 받아주세요.";
    }

    @Override
    public String getComment() {
        return "토큰이 만료된 경우에 발생합니다.";
    }
}
