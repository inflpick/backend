package com.leesh.inflpick.v2.influencer.domain.exception;

public class MaximumInfluencerKeywordSizeException extends IllegalArgumentException {
    public MaximumInfluencerKeywordSizeException(Integer size) {
        super("Influencer Keyword size cannot exceed 10, input keywords size: " + size);
    }
}
