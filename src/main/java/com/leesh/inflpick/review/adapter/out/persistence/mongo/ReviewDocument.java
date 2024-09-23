package com.leesh.inflpick.review.adapter.out.persistence.mongo;

import com.leesh.inflpick.influencer.core.domain.Influencer;
import com.leesh.inflpick.product.core.Product;
import com.leesh.inflpick.review.core.domain.Review;
import com.leesh.inflpick.review.core.domain.ReviewSource;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.*;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "reviews")
public class ReviewDocument implements Persistable<String> {

    @Getter
    @Id
    private final String id;
    @Getter
    private final String influencerId;
    @Getter
    private final String productId;
    private final String contents;
    private final String uri;
    private final Instant reviewedDate;
    @CreatedBy
    private final String createdBy;
    @CreatedDate
    private final Instant createdDate;
    @LastModifiedBy
    private final String lastModifiedBy;
    @LastModifiedDate
    private final Instant lastModifiedDate;

    @Builder
    public ReviewDocument(String id,
                          String influencerId,
                          String productId,
                          String contents,
                          String uri,
                          Instant reviewedDate,
                          String createdBy,
                          Instant createdDate,
                          String lastModifiedBy,
                          Instant lastModifiedDate) {
        this.id = id;
        this.influencerId = influencerId;
        this.productId = productId;
        this.contents = contents;
        this.uri = uri;
        this.reviewedDate = reviewedDate;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.lastModifiedBy = lastModifiedBy;
        this.lastModifiedDate = lastModifiedDate;
    }

    public static ReviewDocument from(Review review) {
        return ReviewDocument.builder()
                .id(review.getId())
                .influencerId(review.getReviewer().getId())
                .productId(review.getProduct().getId())
                .contents(review.getContents())
                .uri(review.getUri())
                .reviewedDate(review.getReviewedDate())
                .createdDate(review.getCreatedDate())
                .lastModifiedDate(review.getLastModifiedDate())
                .build();
    }

    public Review toEntity(Influencer reviewer, Product product) {
        ReviewSource source = ReviewSource.of(contents, uri, reviewedDate);
        return Review.builder()
                .id(id)
                .reviewer(reviewer)
                .product(product)
                .source(source)
                .createdDate(createdDate)
                .lastModifiedDate(lastModifiedDate)
                .build();
    }

    @Override
    public boolean isNew() {
        return createdDate == null;
    }
}
