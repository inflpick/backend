package com.leesh.inflpick.v2.influencer.application.exception;

import com.leesh.inflpick.v2.common.domain.ErrorCode;
import com.leesh.inflpick.v2.influencer.domain.vo.InfluencerId;
import org.springframework.http.HttpStatus;

public class InfluencerNotFoundException extends RuntimeException implements ErrorCode {

    public InfluencerNotFoundException() {
        super("Influencer not found");
    }

    public InfluencerNotFoundException(InfluencerId influencerId) {
        super("Influencer not found, influencerId: " + influencerId);
    }

    @Override
    public String getCode() {
        return "INFLUENCER_NOT_FOUND";
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }

    @Override
    public String getReason() {
        return "요청한 인플루언서를 찾을 수 없어요.";
    }

    @Override
    public String getAction() {
        return "요청 값을 확인 후 다시 시도해주세요.";
    }

    @Override
    public String getComment() {
        return "요청한 값을 가진 인플루언서를 찾을 수 없을 때 발생합니다.";
    }
}
