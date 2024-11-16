package com.leesh.inflpick.product.application.dto;

import com.leesh.inflpick.product.adapter.out.docs.swagger.ProductRequestDocs;
import com.leesh.inflpick.product.domain.Product;
import com.leesh.inflpick.product.domain.vo.OnlineStoreLink;

import java.util.List;

public record ProductRequest(String name,
                             String description,
                             List<String> keywordIds,
                             List<OnlineStoreRequest> onlineStoreLinks) implements ProductRequestDocs {

    public ProductRequest {
        keywordIds = (keywordIds == null) ? List.of() : keywordIds;
        onlineStoreLinks = (onlineStoreLinks == null) ? List.of() : onlineStoreLinks;
    }

    public Product toEntity() {
        List<OnlineStoreLink> storeLinks = onlineStoreLinks.stream()
                .map(OnlineStoreRequest::toEntity)
                .toList();
        return Product.withoutId(name, description, storeLinks);
    }
}
