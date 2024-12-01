package com.leesh.inflpick.common.controller

import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "검색 API 응답")
interface SearchResponseDocs {

    @Schema(description = "인플루언서 목록", implementation = SearchInfluencerResponse::class, requiredMode = Schema.RequiredMode.REQUIRED)
    fun influencers(): List<SearchInfluencerResponse>

    @Schema(description = "상품 목록", implementation = SearchProductResponse::class, requiredMode = Schema.RequiredMode.REQUIRED)
    fun products(): List<SearchProductResponse>

}
