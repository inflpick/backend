package com.leesh.inflpick.common.controller

data class SearchInfluencerResponse(val id: String, val name: String, val profileImageUrl: String): SearchInfluencerResponseDocs {

    override fun id(): String {
        return this.id
    }

    override fun name(): String {
        return this.name
    }

    override fun profileImageUrl(): String {
        return this.profileImageUrl
    }
}
