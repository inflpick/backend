package com.leesh.inflpick.mock

import com.leesh.inflpick.product.domain.ProductV2
import com.leesh.inflpick.product.service.port.ProductRepositoryV2
import java.util.concurrent.CopyOnWriteArrayList

class FakeProductRepository: ProductRepositoryV2 {

    private val data = CopyOnWriteArrayList<ProductV2>()

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
    }

    override fun save(product: ProductV2): ProductV2 {
        data.removeIf { it == product }
        val updatedProduct = product.copy(id = FakeDomainId())
        data.add(updatedProduct)
        return product
    }
}