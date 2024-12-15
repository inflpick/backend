package com.leesh.inflpick.product.infrastructure

import com.leesh.inflpick.product.domain.OnlineStoreLinkV2
import com.leesh.inflpick.product.domain.OnlineStorePlatformV2
import com.leesh.inflpick.product.domain.ProductId
import com.leesh.inflpick.product.domain.ProductV2
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document(collection = "products")
data class ProductDocumentV2(
    @Id val id: String? = null,
    val name: String,
    val description: String,
    val productImagePath: String,
    val keywordIds: List<String>,
    val onlineStoreLinks: List<OnlineStoreLinkDocumentV2>,
    @CreatedBy val createdBy: String,
    @CreatedDate val createdDate: Instant,
    @LastModifiedBy val lastModifiedBy: String,
    @LastModifiedBy val lastModifiedDate: Instant
) {
    fun toDomain(): ProductV2 {
        return ProductV2(
            id = ProductId.fromString(id!!),
            name = name,
            description = description,
            productImagePath = productImagePath,
            keywordIds = keywordIds,
            onlineStoreLinks = onlineStoreLinks.map {
                val platform: OnlineStorePlatformV2 = OnlineStorePlatformV2.fromString(it.platform)
                val onlineStoreLink = OnlineStoreLinkV2(
                    platform = platform,
                    url = it.url
                )
                onlineStoreLink
            },
            createdBy = createdBy,
            lastModifiedBy = lastModifiedBy,
            createdDate = createdDate,
            lastModifiedDate = lastModifiedDate
        )
    }

    companion object {

        fun fromDomain(id: String? = null, notPersistentDomain: ProductV2): ProductDocumentV2 {
            return ProductDocumentV2(
                id = id,
                name = notPersistentDomain.name,
                description = notPersistentDomain.description,
                productImagePath = notPersistentDomain.productImagePath,
                keywordIds = notPersistentDomain.keywordIds,
                onlineStoreLinks = notPersistentDomain.onlineStoreLinksV2.toList().map {
                    OnlineStoreLinkDocumentV2(
                        platform = it.platform.name,
                        url = it.url
                    )
                },
                createdBy = notPersistentDomain.createdBy,
                createdDate = notPersistentDomain.createdDate,
                lastModifiedBy = notPersistentDomain.lastModifiedBy,
                lastModifiedDate = notPersistentDomain.lastModifiedDate
            )
        }
    }
}
