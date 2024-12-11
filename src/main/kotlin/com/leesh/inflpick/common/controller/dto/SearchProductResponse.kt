package com.leesh.inflpick.common.controller.dto

class SearchProductResponse(val id: String, val name: String, val productImageUrl: String) {

    fun id(): String {
        return this.id
    }

    fun name(): String {
        return this.name
    }

    fun productImageUrl(): String {
        return this.productImageUrl
    }
}
