package com.leesh.inflpick.influencer.infrastructure

import com.leesh.inflpick.influencer.domin.InfluencerId
import com.leesh.inflpick.influencer.domin.InfluencerV2
import com.leesh.inflpick.influencer.domin.SnsPlatformV2
import com.leesh.inflpick.influencer.domin.SnsProfileLinkV2
import org.springframework.data.annotation.*
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document(collection = "influencers")
data class InfluencerDocumentV2(
    @Id val id: String? = null,
    val name: String,
    val introduction: String,
    val profileImagePath: String,
    val keywordIds: List<String>,
    val snsProfileLinks: List<SnsProfileDocumentV2>,
    @CreatedBy val createdBy: String = "",
    @LastModifiedBy val lastModifiedBy: String = "",
    @CreatedDate val createdDate: Instant = Instant.now(),
    @LastModifiedDate val lastModifiedDate: Instant = Instant.now()) {

    fun toDomain(): InfluencerV2 {
        return InfluencerV2(
            id = InfluencerId.fromString(id!!),
            name = name,
            introduction = introduction,
            profileImagePath = profileImagePath,
            keywords = keywordIds,
            snsProfileLinkV2s = snsProfileLinks.map {
                val platform: SnsPlatformV2 = SnsPlatformV2.fromString(it.platform)
                val snsProfileLinkV2 = SnsProfileLinkV2(
                    platform = platform,
                    url = it.url
                )
                snsProfileLinkV2
            },
            createdBy = createdBy,
            lastModifiedBy = lastModifiedBy,
            createdDate = createdDate,
            lastModifiedDate = lastModifiedDate
        )
    }

    companion object {

        fun fromDomain(id: String? = null, domain: InfluencerV2): InfluencerDocumentV2 {
            return InfluencerDocumentV2(
                id = id,
                name = domain.name,
                introduction = domain.introduction,
                profileImagePath = domain.profileImagePath,
                keywordIds = domain.keywords,
                snsProfileLinks = domain.snsProfileLinksV2.toList().map {
                    SnsProfileDocumentV2(
                        platform = it.platform.name,
                        url = it.url
                    )
                },
                createdBy = domain.createdBy,
                lastModifiedBy = domain.lastModifiedBy,
                createdDate = domain.createdDate,
                lastModifiedDate = domain.lastModifiedDate
            )
        }

    }

}