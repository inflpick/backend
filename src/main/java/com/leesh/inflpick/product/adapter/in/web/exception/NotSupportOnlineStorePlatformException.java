package com.leesh.inflpick.product.adapter.in.web.exception;

import com.leesh.inflpick.common.domain.ErrorCode;
import org.springframework.http.HttpStatus;

public class NotSupportOnlineStorePlatformException extends RuntimeException implements ErrorCode {

    public NotSupportOnlineStorePlatformException() {
        super("Not support online store platform");
    }

    public NotSupportOnlineStorePlatformException(String platform) {
        super("Not support online store platform: " + platform);
    }

    @Override
    public String getCode() {
        return "NOT_SUPPORT_ONLINE_STORE_PLATFORM";
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }

    @Override
    public String getReason() {
        return "지원하지 않는 온라인 스토어 플랫폼입니다.";
    }

    @Override
    public String getAction() {
        return "지원하는 온라인 스토어 플랫폼을 입력해주세요.";
    }

    @Override
    public String getComment() {
        return "지원하지 않는 온라인 스토어 플랫폼을 입력한 경우 발생합니다.";
    }
}
