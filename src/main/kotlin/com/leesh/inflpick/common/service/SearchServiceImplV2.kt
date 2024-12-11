package com.leesh.inflpick.common.service

import com.leesh.inflpick.common.controller.dto.SearchInfluencerResponse
import com.leesh.inflpick.common.controller.dto.SearchProductResponse
import com.leesh.inflpick.common.controller.dto.SearchResponse
import com.leesh.inflpick.common.controller.port.SearchService
import com.leesh.inflpick.influencer.service.port.InfluencerRepositoryV2
import com.leesh.inflpick.product.service.port.ProductRepositoryV2
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class SearchServiceImplV2(val influencerRepository: InfluencerRepositoryV2, val productRepository: ProductRepositoryV2): SearchService {

    override fun search(keyword: String): SearchResponse {
        val influencerResponses: List<SearchInfluencerResponse> = influencerRepository.searchByName(keyword)
            .map { SearchInfluencerResponse(it.id, it.name, it.profileImagePath) }
        val productResponses: List<SearchProductResponse> = productRepository.searchByName(keyword)
            .map { SearchProductResponse(it.id, it.name, it.productImagePath) }
        return SearchResponse(influencers = influencerResponses, products = productResponses)
    }
}