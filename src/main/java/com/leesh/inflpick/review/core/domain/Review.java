package com.leesh.inflpick.review.core.domain;

import com.leesh.inflpick.influencer.core.domain.Influencer;
import com.leesh.inflpick.product.core.domain.Product;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

public class Review {

    @Getter
    private final String id;
    @Getter
    private final Influencer reviewer;
    @Getter
    private final Product product;
    private final ReviewSource source;
    @Getter
    private final Instant createdDate;
    @Getter
    private final Instant lastModifiedDate;

    @Builder
    public Review(String id,
                  Influencer reviewer,
                  Product product,
                  ReviewSource source,
                  Instant createdDate,
                  Instant lastModifiedDate) {
        this.id = id;
        this.reviewer = reviewer;
        this.product = product;
        this.source = source;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getContents() {
        return source.contents();
    }

    public String getUri() {
        return source.uri();
    }

    public Instant getReviewedDate() {
        return source.reviewedDate();
    }
}
