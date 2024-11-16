package com.leesh.inflpick.review.adapter.out.persistence;

import com.leesh.inflpick.influencer.domain.vo.InfluencerId;
import com.leesh.inflpick.product.domain.vo.ProductId;
import com.leesh.inflpick.review.domain.Review;
import com.leesh.inflpick.review.domain.vo.ReviewId;
import com.leesh.inflpick.review.domain.vo.ReviewSource;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "reviews")
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
        String id = review.id().isEmpty() ? null : review.id().id();
        return new ReviewDocument(
                id,
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
        return Review.withId(
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
