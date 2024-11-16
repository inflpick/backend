package com.leesh.inflpick.review.adapter.out.docs.swagger;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;

@Schema(name = "리뷰 요청")
public interface ReviewRequestDocs {

    @Schema(description = "인플루언서 ID", example = "6726946b272157735138c837")
    String influencerId();

    @Schema(description = "제품 ID", example = "6726946b272157735138c837")
    String productId();

    @Schema(description = "리뷰 내용", example = "좋아요")
    String contents();

    @Schema(description = "리뷰 URL", example = "https://www.naver.com")
    String url();

    @Schema(description = "리뷰 날짜", example = "2021-08-01T00:00:00Z")
    Instant reviewDate();
}
