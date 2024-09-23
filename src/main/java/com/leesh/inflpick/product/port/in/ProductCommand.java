package com.leesh.inflpick.product.port.in;

import com.leesh.inflpick.common.port.out.UuidHolder;
import com.leesh.inflpick.product.core.Product;
import com.leesh.inflpick.product.core.value.OnlineStoreLinks;
import com.leesh.inflpick.product.core.value.ProductDescription;
import com.leesh.inflpick.product.core.value.ProductName;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public record ProductCommand(@NotNull ProductName name,
                             @NotNull ProductDescription description,
                             @NotNull Set<String> keywordUuids,
                             @NotNull OnlineStoreLinks onlineStoreLinks) {

    public Product toEntity(UuidHolder uuidHolder) {
        return Product.builder()
                .uuid(uuidHolder.uuid())
                .name(name)
                .description(description)
                .onlineStoreLinks(onlineStoreLinks)
                .build();
    }
}
