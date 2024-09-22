package com.leesh.inflpick.product.core.value;

import org.jetbrains.annotations.NotNull;

import java.util.Set;

public record OnlineStoreLink(@NotNull OnlineStore store,
                              @NotNull String link) {
    public static OnlineStoreLink of(OnlineStore store, String link) {
        return new OnlineStoreLink(store, link);
    }
}
