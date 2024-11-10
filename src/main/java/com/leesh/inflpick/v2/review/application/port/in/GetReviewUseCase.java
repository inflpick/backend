package com.leesh.inflpick.v2.review.application.port.in;

import com.leesh.inflpick.v2.common.application.dto.CursorResponse;
import com.leesh.inflpick.v2.review.application.dto.CursorRequest;
import com.leesh.inflpick.v2.review.application.dto.ReviewResponse;
import com.leesh.inflpick.v2.review.domain.vo.ReviewId;

public interface GetReviewUseCase {

    CursorResponse<ReviewResponse> getCursorPage(CursorRequest request);

    ReviewResponse get(ReviewId reviewId);
}
