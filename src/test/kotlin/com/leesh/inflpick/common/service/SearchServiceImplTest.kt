package com.leesh.inflpick.common.service

import com.leesh.inflpick.common.controller.dto.SearchResponse
import com.leesh.inflpick.influencer.domin.InfluencerV2
import com.leesh.inflpick.mock.FakeInfluencerRepository
import com.leesh.inflpick.mock.FakeProductRepository
import com.leesh.inflpick.product.domain.ProductV2
import io.kotest.core.spec.DisplayName
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldNotContain
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe

@DisplayName("Service Layer 테스트: SearchServiceImpl")
class SearchServiceImplTest : BehaviorSpec({

    Given("DB에 해당 키워드를 가진 데이터가 존재할 때") {

        val testInfluencers = listOf(
            InfluencerV2(name = "testInfluencer1"),
            InfluencerV2(name = "testInfluencer2"),
            InfluencerV2(name = "testInfluencer3"),
            InfluencerV2(name = "influencer4"),
        )

        val testProducts = listOf(
            ProductV2(name = "testProduct1"),
            ProductV2(name = "testProduct2"),
            ProductV2(name = "testProduct3"),
            ProductV2(name = "product4"),
        )

        val searchService = SearchServiceImplV2(
            influencerRepository = FakeInfluencerRepository(data = testInfluencers),
            productRepository = FakeProductRepository(data = testProducts)
            )

        When("검색을 실행하면") {

            val searchResponse: SearchResponse = searchService.search("test")

            Then("검색 결과를 반환한다") {
                searchResponse.shouldNotBeNull()
                searchResponse.influencers.size.shouldBe(3)
                searchResponse.influencers.map { it.name }.containsAll(listOf("testInfluencer1", "testInfluencer2", "testInfluencer3")).shouldBe(true)
                searchResponse.influencers.map { it.name }.shouldNotContain("influencer4")
                searchResponse.products.size.shouldBe(3)
                searchResponse.products.map { it.name }.containsAll(listOf("testProduct1", "testProduct2", "testProduct3")).shouldBe(true)
                searchResponse.products.map { it.name }.shouldNotContain("product4")
            }
        }
    }

})
