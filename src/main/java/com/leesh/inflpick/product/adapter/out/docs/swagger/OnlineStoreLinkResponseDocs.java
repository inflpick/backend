package com.leesh.inflpick.product.adapter.out.docs.swagger;

import com.leesh.inflpick.product.domain.vo.OnlineStorePlatform;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "온라인 스토어 링크 응답")
public interface OnlineStoreLinkResponseDocs {

    @Schema(description = "플랫폼 이름", example = "COUPANG", implementation = OnlineStorePlatform.class, requiredMode = Schema.RequiredMode.REQUIRED)
    String platform();

    @Schema(description = "온라인 스토어 URL (URL 형식의 문자열)", example = "https://shopping.naver.com", requiredMode = Schema.RequiredMode.REQUIRED)
    String url();
}
