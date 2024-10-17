package com.leesh.inflpick.review.adapter.in.web.docs;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;

@Schema(name = "인플루언서 리뷰 생성 요청", description = "인플루언서 리뷰 생성 요청 필드")
public interface ReviewRequestApiDocs {

    @Schema(description = "리뷰한 인플루언서 ID", example = "92624c72-1cf2-4762-8c45-fe1a1f0a3e97", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
    String influencerId();

    @Schema(description = "리뷰할 제품 ID", example = "92624c72-1cf2-4762-8c45-fe1a1f0a3e97", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
    String productId();

    @Schema(description = "리뷰 내용", example = "이 제품은 정말 좋아요!", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
    String reviewContents();

    @Schema(description = "리뷰 소스 URI", example = "https://www.instagram.com/p/1234567890", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
    String reviewSourceUri();

    @Schema(description = "리뷰한 날짜 (UTC)", example = "2021-07-01T00:00:00Z", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
    Instant reviewedDate();
}
