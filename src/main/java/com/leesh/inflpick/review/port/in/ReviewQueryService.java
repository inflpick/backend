package com.leesh.inflpick.review.port.in;

import com.leesh.inflpick.v2.shared.application.dto.CursorPage;
import com.leesh.inflpick.review.core.domain.Review;
import com.leesh.inflpick.review.port.ReviewCursorQuery;

public interface ReviewQueryService {

    CursorPage<Review> getCursorPage(ReviewCursorQuery query);

}
