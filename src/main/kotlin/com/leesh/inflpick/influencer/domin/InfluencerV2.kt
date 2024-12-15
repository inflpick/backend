package com.leesh.inflpick.influencer.domin

import com.leesh.inflpick.common.domain.DomainId
import com.leesh.inflpick.common.domain.UnsavedId
import java.time.Instant

class InfluencerV2(
    val id: DomainId = UnsavedId(),
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

    override fun equals(other: Any?): Boolean {
        return other is InfluencerV2 && id == other.id
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }

    fun copy(
        id: DomainId = this.id,
        name: String = this.name,
        introduction: String = this.introduction,
        profileImagePath: String = this.profileImagePath,
        keywords: List<String> = this.keywords,
        snsProfileLinkV2s: List<SnsProfileLinkV2> = this.snsProfileLinksV2.toList(),
        createdBy: String = this.createdBy,
        createdDate: Instant = this.createdDate,
    ): InfluencerV2 {
        return InfluencerV2(
            id = id,
            name = name,
            introduction = introduction,
            profileImagePath = profileImagePath,
            keywords = keywords,
            snsProfileLinkV2s = snsProfileLinkV2s,
            createdBy = createdBy,
            createdDate = createdDate,
        )
    }

    fun idToString(): String {
        return id.toString()
    }
}