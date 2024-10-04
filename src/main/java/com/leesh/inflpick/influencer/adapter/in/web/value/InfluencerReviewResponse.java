package com.leesh.inflpick.influencer.adapter.in.web.value;

import com.leesh.inflpick.influencer.core.domain.Influencer;
import com.leesh.inflpick.product.adapter.in.web.value.ProductResponse;
import com.leesh.inflpick.review.core.domain.Review;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Schema(description = "인플루언서가 리뷰한 제품 응답")
@Builder
public record InfluencerReviewResponse(
        @Schema(description = "리뷰한 인플루언서 정보", implementation = InfluencerResponse.class, requiredMode = Schema.RequiredMode.REQUIRED)
        InfluencerResponse reviewer,
        @ArraySchema(schema = @Schema(description = "리뷰한 제품 목록", implementation = ReviewResponse.class, requiredMode = Schema.RequiredMode.REQUIRED))
        List<ReviewResponse> reviews) {

        public static InfluencerReviewResponse from(Influencer influencer, List<Review> review) {
                InfluencerResponse reviewerResponse = InfluencerResponse.from(influencer);
                List<ReviewResponse> reviewResponses = review.stream()
                        .map(ReviewResponse::from)
                        .toList();
                return InfluencerReviewResponse.builder()
                        .reviewer(reviewerResponse)
                        .reviews(reviewResponses)
                        .build();
        }

        @Schema(description = "제품 리뷰 응답")
        @Builder
        private record ReviewResponse(
                @Schema(description = "리뷰 ID", example = "f103314b-778c-49fc-ae9c-7956794a3bdf", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
                String id,
                @Schema(description = "리뷰 내용", example = "이 제품은 정말 좋아요!", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
                String reviewContents,
                @Schema(description = "리뷰 소스 URI", example = "https://www.instagram.com/p/1234567890", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
                String reviewSourceUri,
                @Schema(description = "리뷰한 날짜 (UTC)", example = "2021-07-01T00:00:00Z", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
                String reviewedDate,
                @Schema(description = "리뷰한 제품 정보", implementation = ProductResponse.class, requiredMode = Schema.RequiredMode.REQUIRED)
                ProductResponse reviewedProduct) {

                private static ReviewResponse from(Review review) {
                        return ReviewResponse.builder()
                                .id(review.getId())
                                .reviewContents(review.getContents())
                                .reviewSourceUri(review.getUri())
                                .reviewedDate(review.getReviewedDate().toString())
                                .reviewedProduct(ProductResponse.from(review.getProduct()))
                                .build();
                }
        }

}
