package com.leesh.inflpick.review.adapter.in.web;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.leesh.inflpick.influencer.adapter.in.web.value.InfluencerResponse;
import com.leesh.inflpick.product.adapter.in.web.value.ProductResponse;
import com.leesh.inflpick.review.core.domain.Review;

import java.time.Instant;

public record ReviewResponse(String reviewContents,
                             String reviewSource,
                             @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
                             Instant reviewedDate,
                             InfluencerResponse reviewer,
                             ProductResponse product) {

    public static ReviewResponse from(Review review) {
        return new ReviewResponse(
                review.getContents(),
                review.getUri(),
                review.getReviewedDate(),
                InfluencerResponse.from(review.getReviewer()),
                ProductResponse.from(review.getProduct())
        );
    }

}
