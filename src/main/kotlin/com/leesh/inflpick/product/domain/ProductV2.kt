package com.leesh.inflpick.product.domain

import java.time.Instant

class ProductV2(
    val id: String = "",
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

    fun isPersistent(): Boolean {
        return id.isNotEmpty()
    }
}