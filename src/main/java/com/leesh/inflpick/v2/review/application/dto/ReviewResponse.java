package com.leesh.inflpick.v2.review.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.leesh.inflpick.v2.influencer.domain.Influencer;
import com.leesh.inflpick.v2.product.domain.Product;
import com.leesh.inflpick.v2.review.domain.Review;

import java.time.Instant;

public record ReviewResponse(String contents,
                             String url,
                             @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
                             Instant reviewDate) {

    public static ReviewResponse from(Review review, Influencer influencer, Product product) {
        return new ReviewResponse(
                review.source().contents(),
                review.source().url(),
                review.createdDate()
        );
    }
}
