package com.leesh.inflpick.common.controller.dto

data class SearchResponse(val influencers: List<SearchInfluencerResponse> = emptyList(),
                          val products: List<SearchProductResponse> = emptyList()) {

    fun influencers(): List<SearchInfluencerResponse> {
        return this.influencers;
    }

    fun products(): List<SearchProductResponse> {
        return this.products;
    }

}
