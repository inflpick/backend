package com.leesh.inflpick.common.controller

import com.leesh.inflpick.common.service.SearchServiceImplV2
import com.leesh.inflpick.influencer.domin.InfluencerV2
import com.leesh.inflpick.mock.FakeInfluencerRepository
import com.leesh.inflpick.mock.FakeProductRepository
import com.leesh.inflpick.product.domain.ProductV2
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.springframework.http.HttpStatus

class SearchControllerTest: BehaviorSpec({

    Given("인플루언서와 제품 데이터가 존재할 때") {

        val service = SearchServiceImplV2(
            influencerRepository = FakeInfluencerRepository(
                data = listOf(
                    InfluencerV2(name = "핏블리"),
                    InfluencerV2(name = "짐종국"),
                    InfluencerV2(name = "마이핏"),
                )
            ),
            productRepository = FakeProductRepository(
                data = listOf(
                    ProductV2(name = "닭가슴살"),
                    ProductV2(name = "단백질 50g 프로틴"),
                    ProductV2(name = "핏"),
                )
            )
        )

        val searchController = SearchController(service = service)

        When("\"핏\" 키워드로 검색을 실행하면") {

            val result = searchController.search("핏")

            Then("200 응답과 \"핏\" 키워드를 가진 데이터들을 응답해야 한다.") {

                result.statusCode.shouldBe(HttpStatus.OK)
                result.body.shouldNotBeNull()
                result.body?.influencers?.size.shouldBe(2)
                result.body?.influencers?.map { it.name() }?.containsAll(listOf("핏블리", "마이핏")).shouldBe(true)
                result.body?.products?.size.shouldBe(1)
                result.body?.products?.map { it.name }?.containsAll(listOf("핏"))?.shouldBe(true)

            }

        }

        When("검색어를 빈 입력값으로 입력하면") {
            val result = searchController.search("")

            Then("빈 값으로 응답해야 한다.") {
                result.statusCode.shouldBe(HttpStatus.OK)
                result.body.shouldNotBeNull()
                result.body?.influencers?.size.shouldBe(0)
                result.body?.products?.size.shouldBe(0)
            }
        }
    }

})