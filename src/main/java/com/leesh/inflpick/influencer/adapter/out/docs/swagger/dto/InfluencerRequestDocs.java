package com.leesh.inflpick.influencer.adapter.out.docs.swagger.dto;

import com.leesh.inflpick.influencer.application.dto.SnsProfileLinkRequest;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(name = "인플루언서 등록 요청", description = "인플루언서 등록 요청")
public interface InfluencerRequestDocs {

    @Schema(description = "인플루언서 이름", example = "핏블리", requiredMode = Schema.RequiredMode.REQUIRED)
    String name();

    @Schema(description = "인플루언서 소개", example = "➞ Fitvly (Seokki Moon) PROFILE International Trainer & Sports Nutrition Coach", requiredMode = Schema.RequiredMode.REQUIRED)
    String introduction();

    @ArraySchema(arraySchema = @Schema(description = "인플루언서 키워드 ID 목록",
            example = "[\"6726946b272157735138c837\", \"6726946b272157735138c837\"]",
            defaultValue = "[]",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED),
            maxItems = 10)
    List<String> keywordIds();

    @ArraySchema(arraySchema = @Schema(description = "인플루언서의 SNS 링크 목록",
            example = "[{\"platform\": \"INSTAGRAM\", \"url\": \"https://instagram.com\"}]",
            implementation = SnsProfileLinkRequest.class,
            defaultValue = "[]",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED))
    List<SnsProfileLinkRequest> socialMediaProfileLinks();
}
