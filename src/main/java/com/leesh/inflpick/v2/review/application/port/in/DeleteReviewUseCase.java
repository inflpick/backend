package com.leesh.inflpick.v2.review.application.port.in;

import com.leesh.inflpick.v2.review.domain.vo.ReviewId;

public interface DeleteReviewUseCase {

    void delete(ReviewId id);
}
