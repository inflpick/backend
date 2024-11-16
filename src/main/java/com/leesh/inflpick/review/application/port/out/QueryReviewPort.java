package com.leesh.inflpick.review.application.port.out;

import com.leesh.inflpick.common.application.dto.CursorResponse;
import com.leesh.inflpick.review.application.dto.CursorRequest;
import com.leesh.inflpick.review.domain.Review;
import com.leesh.inflpick.review.domain.vo.ReviewId;

import java.util.Optional;

public interface QueryReviewPort {

    Optional<Review> query(ReviewId id);

    CursorResponse<Review> query(CursorRequest request);
}
