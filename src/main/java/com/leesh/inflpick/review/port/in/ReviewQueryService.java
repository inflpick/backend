package com.leesh.inflpick.review.port.in;

import com.leesh.inflpick.review.core.domain.Review;

import java.util.List;

public interface ReviewQueryService {
    List<Review> getAllByReviewerId(String id);
}
