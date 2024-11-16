package com.leesh.inflpick.review.application.exception;

import com.leesh.inflpick.review.domain.vo.ReviewId;

public class ReviewNotFoundException extends IllegalArgumentException {
    public ReviewNotFoundException(ReviewId id) {
        super("Review not found. id: " + id);
    }
}
