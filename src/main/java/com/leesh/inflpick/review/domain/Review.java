package com.leesh.inflpick.review.domain;

import com.leesh.inflpick.influencer.domain.vo.InfluencerId;
import com.leesh.inflpick.product.domain.vo.ProductId;
import com.leesh.inflpick.review.domain.vo.ReviewId;
import com.leesh.inflpick.review.domain.vo.ReviewSource;

import java.time.Instant;
import java.util.Objects;

public final class Review {
    private final ReviewId id;
    private final ReviewSource source;
    private final InfluencerId influencerId;
    private final ProductId productId;
    private final String createdBy;
    private final Instant createdDate;
    private final String lastModifiedBy;
    private final Instant lastModifiedDate;

    public Review(ReviewId id,
                  ReviewSource source,
                  InfluencerId influencerId,
                  ProductId productId,
                  String createdBy,
                  Instant createdDate,
                  String lastModifiedBy,
                  Instant lastModifiedDate) {
        this.id = id;
        this.source = source;
        this.influencerId = influencerId;
        this.productId = productId;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.lastModifiedBy = lastModifiedBy;
        this.lastModifiedDate = lastModifiedDate;
    }

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

    public ReviewId id() {
        return id;
    }

    public ReviewSource source() {
        return source;
    }

    public InfluencerId influencerId() {
        return influencerId;
    }

    public ProductId productId() {
        return productId;
    }

    public String createdBy() {
        return createdBy;
    }

    public Instant createdDate() {
        return createdDate;
    }

    public String lastModifiedBy() {
        return lastModifiedBy;
    }

    public Instant lastModifiedDate() {
        return lastModifiedDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Review) obj;
        return Objects.equals(this.id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
