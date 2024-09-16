package com.leesh.inflpick.review.domain;

import com.leesh.inflpick.common.domain.Metadata;
import com.leesh.inflpick.influencer.core.domain.InfluencerId;
import com.leesh.inflpick.product.domain.ProductId;

import java.time.Instant;

public class Review {

    private final ReviewId id;
    private final String content;
    private final InfluencerId reviewer;
    private final ProductId product;
    private Instant reviewDate;
    private Metadata metadata;

    public Review(ReviewId id, String content, InfluencerId reviewer, ProductId product) {
        this.id = id;
        this.content = content;
        this.reviewer = reviewer;
        this.product = product;
    }
}
