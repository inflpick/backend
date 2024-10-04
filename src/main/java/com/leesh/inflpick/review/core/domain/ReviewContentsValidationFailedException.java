package com.leesh.inflpick.review.core.domain;

import java.text.MessageFormat;

public class ReviewContentsValidationFailedException extends RuntimeException {

    public static final MessageFormat ERROR_MESSAGE_FORMAT = new MessageFormat("리뷰 내용은 최소 %d자 이상, %d자 이하로 입력해주세요.".formatted(ReviewSource.CONTENTS_MIN_LENGTH, ReviewSource.CONTENTS_MAX_LENGTH));

    public ReviewContentsValidationFailedException(String input) {
        super(ERROR_MESSAGE_FORMAT.format(new Object[]{input}));
    }
}
