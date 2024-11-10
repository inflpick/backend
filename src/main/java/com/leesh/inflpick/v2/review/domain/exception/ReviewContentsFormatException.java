package com.leesh.inflpick.v2.review.domain.exception;

public class ReviewContentsFormatException extends IllegalArgumentException {
    public ReviewContentsFormatException(String contents) {
        super("Review Contents must be between 1 and 50000 characters long" + contents.length());
    }
}
