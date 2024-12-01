package com.leesh.inflpick.common.controller

data class SearchResponse(val influencers: List<SearchInfluencerResponse> = emptyList(),
                          val products: List<SearchProductResponse> = emptyList()): SearchResponseDocs {

    override fun influencers(): List<SearchInfluencerResponse> {
        return this.influencers;
    }

    override fun products(): List<SearchProductResponse> {
        return this.products;
    }

}
