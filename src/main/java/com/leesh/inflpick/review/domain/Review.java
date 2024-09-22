package com.leesh.inflpick.review.domain;

import com.leesh.inflpick.common.core.domain.Metadata;
import com.leesh.inflpick.influencer.core.domain.value.InfluencerId;
import com.leesh.inflpick.product.core.value.ProductId;

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
