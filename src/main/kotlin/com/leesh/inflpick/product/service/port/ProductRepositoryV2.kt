package com.leesh.inflpick.product.service.port

import com.leesh.inflpick.product.domain.ProductV2


interface ProductRepositoryV2 {
    fun save(product: ProductV2): ProductV2
    fun saveAll(products: List<ProductV2>): List<ProductV2>
    fun searchByName(keyword: String): List<ProductV2>
}