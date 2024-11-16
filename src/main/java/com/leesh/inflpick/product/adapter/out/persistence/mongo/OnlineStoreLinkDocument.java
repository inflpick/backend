package com.leesh.inflpick.product.adapter.out.persistence.mongo;

import com.leesh.inflpick.product.domain.vo.OnlineStoreLink;
import com.leesh.inflpick.product.domain.vo.OnlineStorePlatform;

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
