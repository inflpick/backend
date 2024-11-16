package com.leesh.inflpick.review.application.port.in;

import com.leesh.inflpick.common.application.dto.CursorResponse;
import com.leesh.inflpick.review.application.dto.CursorRequest;
import com.leesh.inflpick.review.application.dto.ReviewResponse;
import com.leesh.inflpick.review.domain.vo.ReviewId;

public interface GetReviewUseCase {

    CursorResponse<ReviewResponse> getCursorPage(CursorRequest request);

    ReviewResponse get(ReviewId reviewId);
}
