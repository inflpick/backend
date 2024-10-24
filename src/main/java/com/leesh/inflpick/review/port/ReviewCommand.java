package com.leesh.inflpick.review.port;

import com.leesh.inflpick.v2.shared.adapter.out.uuid.UuidHolder;
import com.leesh.inflpick.influencer.core.domain.Influencer;
import com.leesh.inflpick.product.core.domain.Product;
import com.leesh.inflpick.review.core.domain.Review;
import com.leesh.inflpick.review.core.domain.ReviewSource;

public record ReviewCommand(ReviewSource reviewSource) {
    public static ReviewCommand of(ReviewSource reviewSource) {
        return new ReviewCommand(reviewSource);
    }

    public Review toEntity(UuidHolder uuidHolder, Influencer reviewer, Product product) {
        return Review.builder()
                .id(uuidHolder.uuid())
                .reviewer(reviewer)
                .product(product)
                .source(reviewSource)
                .build();
    }
}
