package com.leesh.inflpick.v2.influencer.domain.exception;

import com.leesh.inflpick.v2.common.domain.ErrorCode;
import org.springframework.http.HttpStatus;

public class MaximumInfluencerKeywordSizeException extends RuntimeException implements ErrorCode {

    public MaximumInfluencerKeywordSizeException() {
        super("Influencer Keyword size cannot exceed 10");
    }

    public MaximumInfluencerKeywordSizeException(Integer size) {
        super("Influencer Keyword size cannot exceed 10, input keywords size: " + size);
    }

    @Override
    public String getCode() {
        return "MAXIMUM_INFLUENCER_KEYWORD_SIZE";
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }

    @Override
    public String getReason() {
        return "인플루언서 키워드는 10개를 초과할 수 없어요.";
    }

    @Override
    public String getAction() {
        return "인플루언서 키워드를 10개 이하로 입력해주세요.";
    }

    @Override
    public String getComment() {
        return "인플루언서 키워드는 10개를 초과할 수 없습니다.";
    }
}
