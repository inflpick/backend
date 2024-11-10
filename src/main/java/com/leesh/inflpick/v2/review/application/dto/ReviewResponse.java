package com.leesh.inflpick.v2.review.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.leesh.inflpick.v2.influencer.application.dto.InfluencerResponse;
import com.leesh.inflpick.v2.product.application.dto.ProductResponse;
import com.leesh.inflpick.v2.review.adapter.out.docs.swagger.ReviewResponseDocs;
import com.leesh.inflpick.v2.review.domain.Review;

import java.time.Instant;

public record ReviewResponse(String id,
                             String contents,
                             String url,
                             @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
                             Instant reviewDate,
                             InfluencerResponse influencer,
                             ProductResponse product) implements ReviewResponseDocs {

    public static ReviewResponse create(Review review, InfluencerResponse influencer, ProductResponse product) {
        return new ReviewResponse(
                review.id().id(),
                review.source().contents(),
                review.source().url(),
                review.source().reviewDate(),
                influencer,
                product
        );
    }
}
