package com.leesh.inflpick.influencer.domin

data class SnsProfileLinksV2(val profiles: List<SnsProfileLinkV2>) {
    fun toList(): List<SnsProfileLinkV2> {
        return profiles
    }
}
