package com.leesh.inflpick.v2.review.application.port.out;

import com.leesh.inflpick.v2.review.domain.Review;
import com.leesh.inflpick.v2.review.domain.vo.ReviewId;

public interface CommandReviewPort {
    ReviewId save(Review review);
}
