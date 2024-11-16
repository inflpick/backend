package com.leesh.inflpick.v2.common.adapter.in.web.exception;

import com.leesh.inflpick.v2.common.domain.ErrorCode;
import org.springframework.http.HttpStatus;

public class NotSupportHttpMediaTypeException extends RuntimeException implements ErrorCode {

    public NotSupportHttpMediaTypeException() {
        super("지원하지 않는 HTTP 미디어 타입이에요.");
    }

    @Override
    public String getCode() {
        return "UNSUPPORTED_HTTP_MEDIA_TYPE";
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.UNSUPPORTED_MEDIA_TYPE;
    }

    @Override
    public String getReason() {
        return "지원하지 않는 HTTP 미디어 타입이에요.";
    }

    @Override
    public String getAction() {
        return "요청을 확인 후 다시 요청해주세요.";
    }

    @Override
    public String getComment() {
        return "API 요청 시, 지원하지 않는 HTTP 미디어 타입을 사용한 경우에 발생합니다.";
    }
}
