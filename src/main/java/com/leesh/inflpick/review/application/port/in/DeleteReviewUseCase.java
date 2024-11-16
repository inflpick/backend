package com.leesh.inflpick.review.application.port.in;

import com.leesh.inflpick.review.domain.vo.ReviewId;

public interface DeleteReviewUseCase {

    void delete(ReviewId id);
}
