package com.leesh.inflpick.product.adapter.in.web.value;

import com.leesh.inflpick.common.adapter.in.web.RequiredFieldsValidator;
import com.leesh.inflpick.product.core.domain.value.OnlineStore;
import com.leesh.inflpick.product.core.domain.value.OnlineStoreLink;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import org.jetbrains.annotations.Nullable;

@Schema(name = "온라인 스토어 링크 요청", description = "온라인 스토어 링크 생성 요청 필드")
@Builder
public record OnlineStoreLinkRequest(
        @Schema(description = "온라인 스토어 플랫폼", example = "COUPANG", implementation = OnlineStore.class, requiredMode = Schema.RequiredMode.REQUIRED)
        String platform,
        @Schema(description = "온라인 스토어 링크 URI (URI 형식의 문자열)", example = "https://link.coupang.com/a/bS7Spe", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
        String url) {

    public OnlineStoreLinkRequest(@Nullable String platform,
                                  @Nullable String url) {
        RequiredFieldsValidator.validate(platform, url);
        assert platform != null;
        this.platform = platform.strip();
        assert url != null;
        this.url = url.strip();
    }

    public OnlineStoreLink toEntity() {
            OnlineStore onlineStore = OnlineStore.from(platform);
            return OnlineStoreLink.of(
                    onlineStore,
                    url);
    }


}
