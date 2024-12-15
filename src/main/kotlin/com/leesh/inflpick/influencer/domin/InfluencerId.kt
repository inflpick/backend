package com.leesh.inflpick.influencer.domin

import com.leesh.inflpick.common.domain.DomainId

class InfluencerId(private val id: String): DomainId {
    override fun isSaved(): Boolean {
        return id.isNotEmpty()
    }

    override fun toString(): String {
        return id
    }

    companion object {
        fun fromString(id: String): InfluencerId {
            return InfluencerId(id)
        }
    }
}