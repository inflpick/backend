package com.leesh.inflpick.v2.review.application.port.out;

import com.leesh.inflpick.v2.influencer.domain.vo.InfluencerId;
import com.leesh.inflpick.v2.product.domain.vo.ProductId;
import com.leesh.inflpick.v2.review.application.dto.GetCursorPageResponse;
import com.leesh.inflpick.v2.review.domain.Review;
import com.leesh.inflpick.v2.review.domain.vo.ReviewId;

import java.time.Instant;
import java.util.Optional;

public interface QueryReviewPort {

    Optional<Review> query(ReviewId id);

    GetCursorPageResponse<Review> query(InfluencerId influencerId, ProductId productId, Instant cursor, Integer limit);
}
