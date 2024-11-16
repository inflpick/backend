package com.leesh.inflpick.influencer.adapter.out.docs.swagger.dto;

import com.leesh.inflpick.influencer.domain.vo.SnsPlatform;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "SNS 프로필 링크 생성 요청", description = "SNS 프로필 링크 생성 요청")
public interface SnsProfileLinkRequestDocs {

    @Schema(description = "SNS 플랫폼", implementation = SnsPlatform.class, example = "INSTAGRAM", requiredMode = Schema.RequiredMode.REQUIRED)
    String platform();

    @Schema(description = "SNS 프로필 링크 URL", example = "https://instagram.com", requiredMode = Schema.RequiredMode.REQUIRED)
    String url();
}
