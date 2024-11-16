package com.leesh.inflpick.review.application.port.in;

import com.leesh.inflpick.review.application.dto.ReviewRequest;
import com.leesh.inflpick.review.domain.vo.ReviewId;

public interface CreateReviewUseCase {

    ReviewId create(ReviewRequest request);

}
