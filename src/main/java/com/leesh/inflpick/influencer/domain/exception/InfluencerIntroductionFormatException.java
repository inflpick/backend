package com.leesh.inflpick.influencer.domain.exception;

import com.leesh.inflpick.common.domain.ErrorCode;
import org.springframework.http.HttpStatus;

public class InfluencerIntroductionFormatException extends RuntimeException implements ErrorCode {

    public InfluencerIntroductionFormatException() {
        super("InfluencerIntroduction must be between 0 and 1000 characters long");
    }

    public InfluencerIntroductionFormatException(String introduction) {
        super("InfluencerIntroduction must be between 0 and 1000 characters long" + introduction.length());
    }

    @Override
    public String getCode() {
        return "INFLUENCER_INTRODUCTION_FORMAT";
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }

    @Override
    public String getReason() {
        return "소개글은 0자 이상 1000자 이하로 작성해주세요.";
    }

    @Override
    public String getAction() {
        return "소개글을 다시 작성해주세요.";
    }

    @Override
    public String getComment() {
        return "소개글이 0자 이하거나 1000자를 초과하는 경우 발생합니다.";
    }
}
