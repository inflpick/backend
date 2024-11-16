package com.leesh.inflpick.influencer.domain.exception;

import com.leesh.inflpick.common.domain.ErrorCode;
import org.springframework.http.HttpStatus;

public class NotSupportedSnsPlatformException extends RuntimeException implements ErrorCode {

    public NotSupportedSnsPlatformException() {
        super("not supported sns platform");
    }

    public NotSupportedSnsPlatformException(String platform) {
        super("not supported sns platform: " + platform);
    }

    @Override
    public String getCode() {
        return "NOT_SUPPORTED_SNS_PLATFORM";
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }

    @Override
    public String getReason() {
        return "지원하지 않는 SNS 플랫폼이에요.";
    }

    @Override
    public String getAction() {
        return "지원하는 SNS 플랫폼을 확인 후 다시 시도해주세요.";
    }

    @Override
    public String getComment() {
        return "지원하지 않는 SNS 플랫폼을 사용하여 API 요청을 하는 경우에 발생합니다.";
    }
}
