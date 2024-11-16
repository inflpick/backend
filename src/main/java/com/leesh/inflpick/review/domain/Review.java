package com.leesh.inflpick.review.domain;

import com.leesh.inflpick.influencer.domain.vo.InfluencerId;
import com.leesh.inflpick.product.domain.vo.ProductId;
import com.leesh.inflpick.review.domain.vo.ReviewId;
import com.leesh.inflpick.review.domain.vo.ReviewSource;

import java.time.Instant;

public record Review(ReviewId id,
                     ReviewSource source,
                     InfluencerId influencerId,
                     ProductId productId,
                     String createdBy,
                     Instant createdDate,
                     String lastModifiedBy,
                     Instant lastModifiedDate) {

    /* Business Logic */
    public static Review withId(ReviewId id,
                                ReviewSource source,
                                InfluencerId influencerId,
                                ProductId productId,
                                String createdBy,
                                Instant createdDate,
                                String lastModifiedBy,
                                Instant lastModifiedDate) {
        return new Review(id,
                source,
                influencerId,
                productId,
                createdBy,
                createdDate,
                lastModifiedBy,
                lastModifiedDate);
    }

    public static Review withoutId(ReviewSource source,
                                   InfluencerId influencerId,
                                   ProductId productId) {
        ReviewId emptyId = ReviewId.empty();
        return new Review(emptyId,
                source,
                influencerId,
                productId,
                null,
                null,
                null,
                null);
    }

    public Review update(InfluencerId influencerId, ProductId productId, ReviewSource source) {
        return withId(id, source, influencerId, productId, createdBy, createdDate, lastModifiedBy, lastModifiedDate);
    }
}
