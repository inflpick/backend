package com.leesh.inflpick.v2.product.adapter.out.persistence.mongo;

import com.leesh.inflpick.v2.product.domain.vo.OnlineStoreLink;
import com.leesh.inflpick.v2.product.domain.vo.OnlineStorePlatform;

public record OnlineStoreLinkDocument(String platform, String url) {

    /* Business Logic */
    public static OnlineStoreLinkDocument from(OnlineStoreLink onlineStoreLink) {
        String platform = onlineStoreLink.platform().name();
        String url = onlineStoreLink.url();
        return new OnlineStoreLinkDocument(platform, url);
    }

    public OnlineStoreLink toEntity() {
        OnlineStorePlatform platform = OnlineStorePlatform.valueOf(this.platform);
        return OnlineStoreLink.create(platform, url);
    }
}
