package com.leesh.inflpick.mock

import com.leesh.inflpick.product.domain.ProductV2
import com.leesh.inflpick.product.infrastructure.ProductDocumentV2
import com.leesh.inflpick.product.service.port.ProductRepositoryV2
import java.util.*
import java.util.concurrent.CopyOnWriteArrayList

class FakeProductRepository: ProductRepositoryV2 {

    private val data = CopyOnWriteArrayList<ProductDocumentV2>()

    constructor() {

    }

    constructor(data: List<ProductV2>) {
        this.saveAll(data)
    }

    override fun saveAll(products: List<ProductV2>): List<ProductV2> {
        return products.map { save(it) }
    }

    override fun searchByName(keyword: String): List<ProductV2> {
        return data.filter { it.name.contains(keyword) }
            .map { it.toDomain() }
    }

    override fun save(product: ProductV2): ProductV2 {
        val productDocumentV2 = when {
            product.isPersistent() -> data.find { it.id == product.id }
                ?.also { data.remove(it) }
                ?.let { ProductDocumentV2.fromDomain(notPersistentDomain = product) }
            else -> ProductDocumentV2.fromDomain(UUID.randomUUID().toString(), product)
        }
        productDocumentV2?.let { data.add(it) }
        return productDocumentV2?.toDomain() ?: product
    }
}