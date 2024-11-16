package com.leesh.inflpick.product.application.dto;

import com.leesh.inflpick.product.adapter.out.docs.swagger.OnlineStoreLinkResponseDocs;
import com.leesh.inflpick.product.domain.vo.OnlineStoreLink;

public record OnlineStoreLinkResponse(String platform, String url) implements OnlineStoreLinkResponseDocs {
    public static OnlineStoreLinkResponse create(OnlineStoreLink onlineStoreLink) {
        return new OnlineStoreLinkResponse(
                onlineStoreLink.platform().name(),
                onlineStoreLink.url());
    }
}
