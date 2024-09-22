package com.leesh.inflpick.product.core.value;

import org.jetbrains.annotations.NotNull;

public record OnlineStoreLink(@NotNull OnlineStore platform,
                              @NotNull String uri) {
    public static OnlineStoreLink of(OnlineStore store, String link) {
        return new OnlineStoreLink(store, link);
    }
}
