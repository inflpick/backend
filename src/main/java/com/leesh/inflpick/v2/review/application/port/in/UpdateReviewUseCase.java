package com.leesh.inflpick.v2.review.application.port.in;

import com.leesh.inflpick.v2.review.application.dto.ReviewRequest;
import com.leesh.inflpick.v2.review.domain.vo.ReviewId;

public interface UpdateReviewUseCase {

    void update(ReviewId id, ReviewRequest request);
}
