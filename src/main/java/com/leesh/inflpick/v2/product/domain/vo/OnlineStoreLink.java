package com.leesh.inflpick.v2.product.domain.vo;

public record OnlineStoreLink(OnlineStorePlatform platform,
                              String url) {

    /* Business Logic */
    public static OnlineStoreLink create(OnlineStorePlatform platform, String url) {
        return new OnlineStoreLink(platform, url);
    }

    public static OnlineStoreLink empty() {
        return new OnlineStoreLink(OnlineStorePlatform.EMPTY, "");
    }
}
