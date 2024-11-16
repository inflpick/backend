package com.leesh.inflpick.product.application.dto;

import com.leesh.inflpick.product.adapter.in.web.exception.NotSupportOnlineStorePlatformException;
import com.leesh.inflpick.product.adapter.out.docs.swagger.OnlineStoreRequestDocs;
import com.leesh.inflpick.product.domain.vo.OnlineStoreLink;
import com.leesh.inflpick.product.domain.vo.OnlineStorePlatform;

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
