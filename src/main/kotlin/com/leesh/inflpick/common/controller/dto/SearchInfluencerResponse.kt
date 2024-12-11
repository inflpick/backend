package com.leesh.inflpick.common.controller.dto

data class SearchInfluencerResponse(val id: String, val name: String, val profileImageUrl: String) {

    fun id(): String {
        return this.id
    }

    fun name(): String {
        return this.name
    }

    fun profileImageUrl(): String {
        return this.profileImageUrl
    }
}
