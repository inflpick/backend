package com.leesh.inflpick.v2.product.application.dto;

import com.leesh.inflpick.v2.product.adapter.in.web.exception.NotSupportOnlineStorePlatformException;
import com.leesh.inflpick.v2.product.adapter.out.docs.swagger.OnlineStoreRequestDocs;
import com.leesh.inflpick.v2.product.domain.vo.OnlineStoreLink;
import com.leesh.inflpick.v2.product.domain.vo.OnlineStorePlatform;

public record OnlineStoreRequest(String platform, String url) implements OnlineStoreRequestDocs {

    public OnlineStoreLink toEntity() {
        try {
            OnlineStorePlatform platform = OnlineStorePlatform.valueOf(this.platform);
            return OnlineStoreLink.create(platform, this.url);
        } catch (IllegalArgumentException e) {
            throw new NotSupportOnlineStorePlatformException(platform);
        }
    }
}
