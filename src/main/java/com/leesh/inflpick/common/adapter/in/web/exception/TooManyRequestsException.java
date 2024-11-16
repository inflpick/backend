package com.leesh.inflpick.common.adapter.in.web.exception;

import com.leesh.inflpick.common.domain.ErrorCode;
import org.springframework.http.HttpStatus;

public class TooManyRequestsException extends RuntimeException implements ErrorCode {

    public TooManyRequestsException() {
        super("요청이 너무 많아요.");
    }

    @Override
    public String getCode() {
        return "TOO_MANY_REQUESTS";
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.TOO_MANY_REQUESTS;
    }

    @Override
    public String getReason() {
        return "요청이 너무 많아요.";
    }

    @Override
    public String getAction() {
        return "잠시 후 다시 시도하시거나, 인플픽 관리자(info@inflpick.com)에게 문의바랍니다.";
    }

    @Override
    public String getComment() {
        return "짧은 시간 동안 많은 API 요청되는 경우에 발생합니다.";
    }
}
