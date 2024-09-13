package com.leesh.inflpick.product.domain;

import com.leesh.inflpick.common.domain.Metadata;

import java.net.URI;

public class Product {

    private final ProductId id;
    private final String name;
    private final Category category;
    private final Price price;
    private final URI imageUri;
    private final Metadata metadata;

    public Product(ProductId id,
                   String name,
                   Category category,
                   Price price,
                   URI imageUri,
                   Metadata metadata) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.imageUri = imageUri;
        this.metadata = metadata;
    }
}
