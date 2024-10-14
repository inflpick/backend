package com.leesh.inflpick.review.adapter.in.web;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.leesh.inflpick.influencer.adapter.in.web.value.InfluencerWebResponse;
import com.leesh.inflpick.product.adapter.in.web.value.ProductWebResponse;
import com.leesh.inflpick.review.core.domain.Review;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;

@Schema(name = "리뷰 응답", description = "리뷰 응답 필드")
public record ReviewResponse(
        @Schema(description = "리뷰 내용", example = "이 제품은 정말 좋아요!", implementation = String.class)
        String reviewContents,
        @Schema(description = "리뷰 소스 URI", example = "https://www.instagram.com/p/1234567890", implementation = String.class)
        String reviewSource,
        @Schema(description = "리뷰한 날짜 (UTC)", example = "2021-07-01T00:00:00Z", implementation = String.class)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
        Instant reviewedDate,
        @Schema(description = "리뷰한 인플루언서 정보", implementation = InfluencerWebResponse.class)
        InfluencerWebResponse reviewer,
        @Schema(description = "리뷰한 제품 정보")
        ProductWebResponse product) {

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
