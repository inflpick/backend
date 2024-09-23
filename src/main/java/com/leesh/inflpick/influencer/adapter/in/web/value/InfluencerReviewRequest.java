package com.leesh.inflpick.influencer.adapter.in.web.value;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.leesh.inflpick.common.adapter.in.web.RequiredFieldsValidator;
import com.leesh.inflpick.review.core.domain.ReviewSource;
import com.leesh.inflpick.review.port.in.ReviewCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import org.jetbrains.annotations.Nullable;

import java.time.Instant;

@Schema(name = "인플루언서 리뷰 생성 요청", description = "인플루언서 리뷰 생성 요청 필드")
@Builder
public record InfluencerReviewRequest(
        @Schema(description = "리뷰할 제품 ID", example = "92624c72-1cf2-4762-8c45-fe1a1f0a3e97", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
        String productId,
        @Schema(description = "리뷰 내용", example = "이 제품은 정말 좋아요!", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
        String reviewContents,
        @Schema(description = "리뷰 소스 URI", example = "https://www.instagram.com/p/1234567890", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
        String reviewSourceUri,
        @Schema(description = "리뷰한 날짜 (UTC)", example = "2021-07-01T00:00:00Z", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
        Instant reviewedDate) {

    public InfluencerReviewRequest(@Nullable String productId,
                                   @Nullable String reviewContents,
                                   @Nullable String reviewSourceUri,
                                   @Nullable Instant reviewedDate) {
        RequiredFieldsValidator.validate(productId, reviewContents, reviewSourceUri, reviewedDate);
        assert productId != null;
        this.productId = productId.strip();
        assert reviewContents != null;
        this.reviewContents = reviewContents.strip();
        assert reviewSourceUri != null;
        this.reviewSourceUri = reviewSourceUri.strip();
        this.reviewedDate = reviewedDate;
    }

    public ReviewCommand toCommand() {
        RequiredFieldsValidator.validate(productId, reviewContents, reviewSourceUri, reviewedDate);
        ReviewSource reviewSource = ReviewSource.of(reviewSourceUri, reviewSourceUri, reviewedDate);
        return ReviewCommand.of(reviewSource);
    }

}
