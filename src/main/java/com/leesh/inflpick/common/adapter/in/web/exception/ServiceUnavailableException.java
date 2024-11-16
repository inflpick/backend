package com.leesh.inflpick.common.adapter.in.web.exception;

import com.leesh.inflpick.common.domain.ErrorCode;
import org.springframework.http.HttpStatus;

public class ServiceUnavailableException extends RuntimeException implements ErrorCode {

    public ServiceUnavailableException() {
        super("현재 서버가 요청을 처리할 수 없는 상태에요.");
    }

    @Override
    public String getCode() {
        return "SERVICE_UNAVAILABLE";
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.SERVICE_UNAVAILABLE;
    }

    @Override
    public String getReason() {
        return "현재 서버가 요청을 처리할 수 없는 상태에요.";
    }

    @Override
    public String getAction() {
        return "잠시 후 다시 시도하시거나, 인플픽 관리자(info@inflpick.com)에게 문의바랍니다.";
    }

    @Override
    public String getComment() {
        return "현재 서버가 점검 중이거나 서비스를 제공할 수 없는 상태, 혹은 처리할 수 없는 에러가 발생한 경우에 발생합니다.";
    }
}
