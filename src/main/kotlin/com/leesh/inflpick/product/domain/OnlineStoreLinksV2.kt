package com.leesh.inflpick.product.domain

data class OnlineStoreLinksV2(val links: List<OnlineStoreLinkV2>) {
    fun toList(): List<OnlineStoreLinkV2> {
        return links
    }
}
