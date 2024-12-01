package com.leesh.inflpick.influencer.domin

enum class SnsPlatformV2 {
    INSTAGRAM,
    YOUTUBE,
    TIKTOK,
    FACEBOOK,
    LINKEDIN,
    X,
    BLOG,
    CAFE,
    ETC
    ;

    companion object {
        fun fromString(value: String): SnsPlatformV2 {
            return when (value) {
                "INSTAGRAM" -> INSTAGRAM
                "YOUTUBE" -> YOUTUBE
                "TIKTOK" -> TIKTOK
                "FACEBOOK" -> FACEBOOK
                "LINKEDIN" -> LINKEDIN
                "X" -> X
                "BLOG" -> BLOG
                "CAFE" -> CAFE
                "ETC" -> ETC
                else -> ETC;
            }
        }
    }
}