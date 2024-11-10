package com.leesh.inflpick.v2.review.application.exception;

import com.leesh.inflpick.v2.review.domain.vo.ReviewId;

public class ReviewNotFoundException extends IllegalArgumentException {
    public ReviewNotFoundException(ReviewId id) {
        super("Review not found. id: " + id);
    }
}
