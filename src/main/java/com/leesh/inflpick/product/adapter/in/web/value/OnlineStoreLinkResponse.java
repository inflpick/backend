package com.leesh.inflpick.product.adapter.in.web.value;

import com.leesh.inflpick.product.adapter.in.web.docs.OnlineStoreLinkResponseApiDocs;
import com.leesh.inflpick.product.core.domain.value.OnlineStoreLink;

public record OnlineStoreLinkResponse(String platform, String url) implements OnlineStoreLinkResponseApiDocs {
    public static OnlineStoreLinkResponse from(OnlineStoreLink onlineStoreLink) {
        return new OnlineStoreLinkResponse(
                onlineStoreLink.platform().name(),
                onlineStoreLink.uri());
    }
}
