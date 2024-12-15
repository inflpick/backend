package com.leesh.inflpick.product.domain

import com.leesh.inflpick.common.domain.DomainId

class ProductId(private val id: String): DomainId {
    override fun isSaved(): Boolean {
        return id.isNotEmpty()
    }

    override fun toString(): String {
        return id
    }

    companion object {
        fun fromString(id: String): DomainId {
            return ProductId(id)
        }
    }
}