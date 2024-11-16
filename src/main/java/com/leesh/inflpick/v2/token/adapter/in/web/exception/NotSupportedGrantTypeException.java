package com.leesh.inflpick.v2.token.adapter.in.web.exception;

import com.leesh.inflpick.v2.common.domain.ErrorCode;
import org.springframework.http.HttpStatus;

public class NotSupportedGrantTypeException extends RuntimeException implements ErrorCode {

    public NotSupportedGrantTypeException() {
        super("Not supported grant type");
    }

    public NotSupportedGrantTypeException(String grantType) {
        super("Not supported grant type, grantType: " + grantType);
    }

    @Override
    public String getCode() {
        return "NOT_SUPPORTED_GRANT_TYPE";
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }

    @Override
    public String getReason() {
        return "지원하지 않는 인증 타입이에요.";
    }

    @Override
    public String getAction() {
        return "인증 타입을 확인 후 다시 시도해주세요";
    }

    @Override
    public String getComment() {
        return "지원하지 않는 인증 타입을 사용하여 토큰을 발급할 수 없을 때 발생합니다.";
    }
}
