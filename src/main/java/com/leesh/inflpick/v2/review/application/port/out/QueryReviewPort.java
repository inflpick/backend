package com.leesh.inflpick.v2.review.application.port.out;

import com.leesh.inflpick.v2.common.application.dto.CursorResponse;
import com.leesh.inflpick.v2.review.application.dto.CursorRequest;
import com.leesh.inflpick.v2.review.domain.Review;
import com.leesh.inflpick.v2.review.domain.vo.ReviewId;

import java.util.Optional;

public interface QueryReviewPort {

    Optional<Review> query(ReviewId id);

    CursorResponse<Review> query(CursorRequest request);
}
