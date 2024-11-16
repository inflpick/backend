package com.leesh.inflpick.review.adapter.out.docs.swagger;

import com.leesh.inflpick.influencer.application.dto.InfluencerResponse;
import com.leesh.inflpick.product.application.dto.ProductResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.time.Instant;

@Tag(name = "리뷰 응답", description = "리뷰 응답")
public interface ReviewResponseDocs {

    @Schema(description = "리뷰 ID", example = "6726946b272157735138c837")
    String id();

    @Schema(description = "contents", example = "좋아요")
    String contents();

    @Schema(description = "url", example = "https://www.naver.com")
    String url();

    @Schema(description = "reviewDate", example = "2021-08-01T00:00:00Z")
    Instant reviewDate();

    @Schema(description = "인플루언서", implementation = InfluencerResponse.class)
    InfluencerResponse influencer();

    @Schema(description = "제품", implementation = ProductResponse.class)
    ProductResponse product();

}
