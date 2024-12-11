package com.leesh.inflpick.product.infrastructure

import com.leesh.inflpick.product.domain.ProductV2
import com.leesh.inflpick.product.service.port.ProductRepositoryV2
import org.springframework.stereotype.Repository

@Repository
class ProductRepositoryImplV2(private val productMongoRepository: ProductMongoRepositoryV2) : ProductRepositoryV2 {
    override fun save(product: ProductV2): ProductV2 {
        TODO("Not yet implemented")
    }

    override fun saveAll(products: List<ProductV2>): List<ProductV2> {
        TODO("Not yet implemented")
    }

    override fun searchByName(keyword: String): List<ProductV2> {
        return productMongoRepository.findAllByNameMatches(keyword)
            .map {
                it.toDomain()
            }
    }
}