package com.leesh.inflpick.v2.product.domain;

import com.leesh.inflpick.v2.product.domain.vo.OnlineStoreLink;

import java.util.List;

public record OnlineStoreLinks(List<OnlineStoreLink> links) {

    /* Business Logic */
    static OnlineStoreLinks create(List<OnlineStoreLink> links) {
        return new OnlineStoreLinks(links);
    }

    static OnlineStoreLinks empty() {
        return new OnlineStoreLinks(List.of());
    }
}
