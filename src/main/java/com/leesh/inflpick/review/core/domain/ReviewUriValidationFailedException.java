package com.leesh.inflpick.review.core.domain;

import java.text.MessageFormat;

public class ReviewUriValidationFailedException extends RuntimeException {

    public static final MessageFormat ERROR_MESSAGE_FORMAT = new MessageFormat("리뷰 URI는 최소 %d자 이상, %d자 이하로 입력해주세요.".formatted(ReviewSource.URI_MIN_LENGTH, ReviewSource.URI_MAX_LENGTH));

    public ReviewUriValidationFailedException(String input) {
        super(ERROR_MESSAGE_FORMAT.format(new Object[]{input}));
    }
}
