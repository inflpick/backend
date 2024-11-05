package com.leesh.inflpick.v2.review.domain;

import com.leesh.inflpick.v2.influencer.domain.vo.InfluencerId;
import com.leesh.inflpick.v2.product.domain.vo.ProductId;
import com.leesh.inflpick.v2.review.domain.vo.ReviewId;
import com.leesh.inflpick.v2.review.domain.vo.ReviewSource;

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
    public static Review withPersistence(ReviewId id,
                                         ReviewSource source,
                                         InfluencerId influencerId,
                                         ProductId productId,
                                         String createdBy,
                                         Instant createdDate,
                                         String lastModifiedBy,
                                         Instant lastModifiedDate) {
        return new Review(id, source, influencerId, productId, createdBy, createdDate, lastModifiedBy, lastModifiedDate);
    }

    public static Review withoutPersistence(ReviewSource source,
                                            InfluencerId influencerId,
                                            ProductId productId) {
        ReviewId emptyId = ReviewId.empty();
        return new Review(emptyId, source, influencerId, productId, "", Instant.now(), "", Instant.now());
    }

    public Review update(InfluencerId influencerId, ProductId productId, ReviewSource source) {
        return withPersistence(id, source, influencerId, productId, createdBy, createdDate, lastModifiedBy, lastModifiedDate);
    }
}
