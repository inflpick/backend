package com.leesh.inflpick.product.domain

import com.leesh.inflpick.common.domain.DomainId
import com.leesh.inflpick.common.domain.UnsavedId
import java.time.Instant

class ProductV2(
    val id: DomainId = UnsavedId(),
    val name: String,
    val description: String = "",
    val productImagePath: String = "",
    val keywordIds: List<String> = emptyList(),
    onlineStoreLinks: List<OnlineStoreLinkV2> = emptyList(),
    val createdBy: String = "",
    val lastModifiedBy: String = "",
    val createdDate: Instant = Instant.now(),
    val lastModifiedDate: Instant = Instant.now()
) {

    val onlineStoreLinksV2: OnlineStoreLinksV2 = OnlineStoreLinksV2(onlineStoreLinks)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as ProductV2
        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    fun isSaved(): Boolean {
        return id.isSaved()
    }

    fun copy(
        id: DomainId = this.id,
        name: String = this.name,
        description: String = this.description,
        productImagePath: String = this.productImagePath,
        keywordIds: List<String> = this.keywordIds,
        onlineStoreLinks: List<OnlineStoreLinkV2> = this.onlineStoreLinksV2.toList())
    : ProductV2 {
        return ProductV2(
            id = id,
            name = name,
            description = description,
            productImagePath = productImagePath,
            keywordIds = keywordIds,
            onlineStoreLinks = onlineStoreLinks,
            createdBy = createdBy,
        )
    }

    fun idToString(): String {
        return id.toString()
    }
}