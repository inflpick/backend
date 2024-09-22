package com.leesh.inflpick.product.core.value;

import org.jetbrains.annotations.NotNull;

import java.util.Set;

public record OnlineStoreLinks(@NotNull Set<OnlineStoreLink> links) {

    public static OnlineStoreLinks from(Set<OnlineStoreLink> links) {
        return new OnlineStoreLinks(links);
    }

    public Set<OnlineStoreLink> getImmutable() {
        return Set.copyOf(links);
    }
}
