package com.leesh.inflpick.v2.influencer.adapter.out.docs.swagger.dto;

import com.leesh.inflpick.v2.influencer.domain.vo.SnsPlatform;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "소셜 미디어 프로필 링크 응답")
public interface SnsProfileLinkResponseDocs {

    @Schema(description = "플랫폼 이름", example = "INSTAGRAM", implementation = SnsPlatform.class, requiredMode = Schema.RequiredMode.REQUIRED)
    String platform();

    @Schema(description = "소셜 미디어 프로필 URL (URL 형식의 문자열)", example = "https://instagram.com/jimjongkook", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
    String url();
}
