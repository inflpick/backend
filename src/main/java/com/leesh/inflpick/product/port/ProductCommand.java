package com.leesh.inflpick.product.port;

import com.leesh.inflpick.v2.adapter.out.uuid.UuidHolder;
import com.leesh.inflpick.product.core.domain.Product;
import com.leesh.inflpick.product.core.domain.value.OnlineStoreLinks;
import com.leesh.inflpick.product.core.domain.value.ProductDescription;
import com.leesh.inflpick.product.core.domain.value.ProductName;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public record ProductCommand(@NotNull ProductName name,
                             @NotNull ProductDescription description,
                             @NotNull Set<String> keywordUuids,
                             @NotNull OnlineStoreLinks onlineStoreLinks) {

    public Product toEntity(UuidHolder uuidHolder) {
        return Product.builder()
                .id(uuidHolder.uuid())
                .name(name)
                .description(description)
                .onlineStoreLinks(onlineStoreLinks)
                .build();
    }
}
