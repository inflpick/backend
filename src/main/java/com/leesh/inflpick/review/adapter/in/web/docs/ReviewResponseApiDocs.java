package com.leesh.inflpick.review.adapter.in.web.docs;


import com.leesh.inflpick.influencer.adapter.in.web.value.InfluencerWebResponse;
import com.leesh.inflpick.product.adapter.in.web.value.ProductWebResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;

@Schema(name = "리뷰 응답", description = "리뷰 응답 필드")
public interface ReviewResponseApiDocs {

    @Schema(description = "리뷰 내용", example = "이 제품은 정말 좋아요!", implementation = String.class)
    String reviewContents();

    @Schema(description = "리뷰 소스 URI", example = "https://www.instagram.com/p/1234567890", implementation = String.class)
    String reviewSource();

    @Schema(description = "리뷰한 날짜 (UTC)", example = "2021-07-01T00:00:00Z", implementation = String.class)
    Instant reviewedDate();

    @Schema(description = "리뷰한 인플루언서 정보", implementation = InfluencerWebResponse.class)
    InfluencerWebResponse reviewer();

    @Schema(description = "리뷰한 제품 정보", implementation = ProductWebResponse.class)
    ProductWebResponse product();
}
