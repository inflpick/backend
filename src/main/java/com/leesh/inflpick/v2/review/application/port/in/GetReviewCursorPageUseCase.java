package com.leesh.inflpick.v2.review.application.port.in;

import com.leesh.inflpick.v2.review.application.dto.GetCursorPageResponse;
import com.leesh.inflpick.v2.review.application.dto.GetReviewCursorPageRequest;
import com.leesh.inflpick.v2.review.application.dto.ReviewResponse;

public interface GetReviewCursorPageUseCase {

    GetCursorPageResponse<ReviewResponse> getCursorPage(GetReviewCursorPageRequest request);
}
