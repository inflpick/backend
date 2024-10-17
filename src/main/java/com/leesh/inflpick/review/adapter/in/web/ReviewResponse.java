package com.leesh.inflpick.review.adapter.in.web;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.leesh.inflpick.influencer.adapter.in.web.value.InfluencerWebResponse;
import com.leesh.inflpick.product.adapter.in.web.value.ProductWebResponse;
import com.leesh.inflpick.review.adapter.in.web.docs.ReviewResponseApiDocs;
import com.leesh.inflpick.review.core.domain.Review;

import java.time.Instant;

public record ReviewResponse(
        String reviewContents,
        String reviewSource,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
        Instant reviewedDate,
        InfluencerWebResponse reviewer,
        ProductWebResponse product) implements ReviewResponseApiDocs {

    public static ReviewResponse from(Review review, InfluencerWebResponse reviewer, ProductWebResponse product) {
        return new ReviewResponse(
                review.getContents(),
                review.getUri(),
                review.getReviewedDate(),
                reviewer,
                product
        );
    }

}
