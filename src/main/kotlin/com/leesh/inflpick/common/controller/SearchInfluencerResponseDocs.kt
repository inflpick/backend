package com.leesh.inflpick.common.controller

import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "인플루언서 목록")
interface SearchInfluencerResponseDocs {

    @Schema(description = "ID", implementation = String::class, required = true, example = "6749744157e33f5cc6da346c")
    fun id(): String

    @Schema(description = "이름", implementation = String::class, required = true, example = "핏블리")
    fun name(): String

    @Schema(description = "프로필 이미지 URL", implementation = String::class, required = true, example = "https://cdn.inflpick.com/profile/6749744157e33f5cc6da346c.jpg")
    fun profileImageUrl(): String
}