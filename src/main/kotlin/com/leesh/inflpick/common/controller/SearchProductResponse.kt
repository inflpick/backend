package com.leesh.inflpick.common.controller

class SearchProductResponse(val id: String, val name: String, val productImageUrl: String): SearchProductResponseDocs {

    override fun id(): String {
        return this.id
    }

    override fun name(): String {
        return this.name
    }

    override fun productImageUrl(): String {
        return this.productImageUrl
    }
}
