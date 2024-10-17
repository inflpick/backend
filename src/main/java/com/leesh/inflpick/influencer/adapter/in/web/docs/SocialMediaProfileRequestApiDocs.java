package com.leesh.inflpick.influencer.adapter.in.web.docs;

import com.leesh.inflpick.influencer.core.domain.value.SocialMediaPlatform;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "소셜 미디어 요청", description = "소셜 미디어 생성 요청 필드")
public interface SocialMediaProfileRequestApiDocs {

    @Schema(description = "소셜 미디어 플랫폼", example = "INSTAGRAM", implementation = SocialMediaPlatform.class, requiredMode = Schema.RequiredMode.REQUIRED)
    String platform();

    @Schema(description = "소셜 미디어 프로필 URI (URI 형식의 문자열)", example = "https://instagram.com/jimjongkook", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
    String uri();

}
