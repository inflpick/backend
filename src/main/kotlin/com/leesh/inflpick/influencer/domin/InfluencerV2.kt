package com.leesh.inflpick.influencer.domin

import java.time.Instant

class InfluencerV2(
    val id: String = "",
    val name: String,
    val introduction: String = "",
    val profileImagePath: String = "",
    val keywords: List<String> = emptyList(),
    snsProfileLinkV2s: List<SnsProfileLinkV2> = emptyList(),
    val createdBy: String = "",
    val lastModifiedBy: String = "",
    val createdDate: Instant = Instant.now(),
    val lastModifiedDate: Instant = Instant.now()
) {

    val snsProfileLinksV2: SnsProfileLinksV2 = SnsProfileLinksV2(snsProfileLinkV2s)

    fun isPersistent(): Boolean {
        return id.isNotEmpty()
    }
}