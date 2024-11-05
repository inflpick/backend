package com.leesh.inflpick.v2.review.adapter.out.persistence;

import com.leesh.inflpick.v2.influencer.domain.vo.InfluencerId;
import com.leesh.inflpick.v2.product.domain.vo.ProductId;
import com.leesh.inflpick.v2.review.domain.Review;
import com.leesh.inflpick.v2.review.domain.vo.ReviewId;
import com.leesh.inflpick.v2.review.domain.vo.ReviewSource;
import org.springframework.data.annotation.*;

import java.time.Instant;

public record ReviewDocument(@Id String id,
                             String contents,
                             String url,
                             Instant reviewDate,
                             String influencerId,
                             String productId,
                             @CreatedBy String createdBy,
                             @CreatedDate Instant createdDate,
                             @LastModifiedBy String lastModifiedBy,
                             @LastModifiedDate Instant lastModifiedDate) {

    public static ReviewDocument from(Review review) {
        return new ReviewDocument(
                review.id().id(),
                review.source().contents(),
                review.source().url(),
                review.source().reviewDate(),
                review.influencerId().id(),
                review.productId().id(),
                review.createdBy(),
                review.createdDate(),
                review.lastModifiedBy(),
                review.lastModifiedDate());
    }

    public Review toEntity() {
        return Review.withPersistence(
                ReviewId.create(id),
                ReviewSource.create(contents, url, reviewDate),
                InfluencerId.create(influencerId),
                ProductId.create(productId),
                createdBy,
                createdDate,
                lastModifiedBy,
                lastModifiedDate);
    }
}
