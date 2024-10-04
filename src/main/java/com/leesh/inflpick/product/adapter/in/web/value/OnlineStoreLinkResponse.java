package com.leesh.inflpick.product.adapter.in.web.value;

import com.leesh.inflpick.product.core.domain.value.OnlineStoreLink;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "온라인 스토어 링크 응답", description = "온라인 스토어 링크 생성 응답 필드")
public record OnlineStoreLinkResponse(
        @Schema(description = "온라인 스토어 플랫폼", example = "COUPANG", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
        String platform,
        @Schema(description = "온라인 스토어 링크 URI (URI 형식의 문자열)", example = "https://link.coupang.com/a/bS7Spe", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
        String url
) {
    public static OnlineStoreLinkResponse from(OnlineStoreLink onlineStoreLink) {
        return new OnlineStoreLinkResponse(
                onlineStoreLink.platform().name(),
                onlineStoreLink.uri());
    }
}
