package com.leesh.inflpick.review.application.port.out;

import com.leesh.inflpick.review.domain.Review;
import com.leesh.inflpick.review.domain.vo.ReviewId;

public interface CommandReviewPort {
    ReviewId save(Review review);

    void delete(ReviewId id);
}
