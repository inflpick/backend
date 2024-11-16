package com.leesh.inflpick.product.domain.vo;

public record OnlineStoreLink(OnlineStorePlatform platform,
                              String url) {

    /* Business Logic */
    public static OnlineStoreLink create(OnlineStorePlatform platform, String url) {
        return new OnlineStoreLink(platform, url);
    }
}
