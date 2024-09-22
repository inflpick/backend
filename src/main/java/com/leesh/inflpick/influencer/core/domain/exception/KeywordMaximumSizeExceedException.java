package com.leesh.inflpick.influencer.core.domain.exception;

import com.leesh.inflpick.influencer.core.domain.value.Keywords;

import java.text.MessageFormat;

public class KeywordMaximumSizeExceedException extends IllegalArgumentException {

   public static final MessageFormat ERROR_MESSAGE_FORMAT = new MessageFormat("키워드는 최대 %d개까지만 등록할 수 있습니다. (현재 등록된 키워드 개수: {0}, 등록 요쳥한 키워드 개수: {1})".formatted(Keywords.MAX_KEYWORD_SIZE));

    public KeywordMaximumSizeExceedException(Integer currentKeywordSize, Integer requestKeywordSize) {
        super(ERROR_MESSAGE_FORMAT.format(new Object[]{currentKeywordSize, requestKeywordSize}));
    }
}
