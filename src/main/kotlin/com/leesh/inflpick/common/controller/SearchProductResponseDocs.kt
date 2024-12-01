package com.leesh.inflpick.common.controller

import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "상품 목록")

interface SearchProductResponseDocs {
    @Schema(description = "ID", example = "6ca2123ac2ka1mz1a", required = true, implementation = String::class)
    fun id(): String

    @Schema(name = "상품 이름", example = "토트백", required = true, implementation = String::class)
    fun name(): String

    @Schema(name = "상품 이미지 URL", example = "https://cdn.inflpick.com/6ca2123ac2ka1mz1a.jpg", required = true, implementation = String::class)
    fun productImageUrl(): String
}