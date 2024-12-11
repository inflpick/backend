package com.leesh.inflpick.product.infrastructure

import org.springframework.data.mongodb.repository.MongoRepository

interface ProductMongoRepositoryV2: MongoRepository<ProductDocumentV2, String> {
    fun findAllByNameMatches(namePattern: String): List<ProductDocumentV2>
}