package com.leesh.inflpick.product.domain

enum class OnlineStorePlatformV2 {
    COUPANG,
    NAVER,
    AMAZON,
    ETC;

    companion object {
        fun fromString(platform: String): OnlineStorePlatformV2 {
            return when (platform) {
                "COUPANG" -> COUPANG
                "NAVER" -> NAVER
                "AMAZON" -> AMAZON
                else -> ETC
            }
        }
    }
}