package com.leesh.inflpick.v2.product.adapter.out.docs.swagger;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "온라인 스토어 링크 생성 요청", description = "온라인 스토어 링크 생성 요청")
public interface OnlineStoreRequestDocs {

    @Schema(description = "온라인 스토어 플랫폼", example = "COUPANG", required = true)
    String platform();

    @Schema(description = "온라인 스토어 링크 URL", example = "https://coupang.com", required = true)
    String url();
}
