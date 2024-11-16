package com.leesh.inflpick.v2.influencer.domain.exception;

import com.leesh.inflpick.v2.common.domain.ErrorCode;
import org.springframework.http.HttpStatus;

public class InfluencerNameFormatException extends RuntimeException implements ErrorCode {

    public InfluencerNameFormatException() {
        super("InfluencerName must be between 1 and 300 characters long");
    }

    public InfluencerNameFormatException(String name) {
        super("InfluencerName must be between 1 and 300 characters long, but was: " + name.length());
    }

    @Override
    public String getCode() {
        return "INFLUENCER_NAME_FORMAT";
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }

    @Override
    public String getReason() {
        return "인플루언서 이름은 1자 이상 300자 이하로 입력해주세요.";
    }

    @Override
    public String getAction() {
        return "인플루언서 이름을 다시 입력해주세요.";
    }

    @Override
    public String getComment() {
        return "인플루언서 이름이 1자 이상 300자 이하로 입력되지 않은 경우에 발생합니다.";
    }
}
