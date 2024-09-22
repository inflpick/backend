package com.leesh.inflpick.product.core;

import com.leesh.inflpick.influencer.core.domain.value.Keywords;
import com.leesh.inflpick.product.core.value.OnlineStoreLinks;
import com.leesh.inflpick.product.core.value.ProductDescription;
import com.leesh.inflpick.product.core.value.ProductImage;
import com.leesh.inflpick.product.core.value.ProductName;
import lombok.Builder;
import lombok.Getter;

import java.nio.file.Path;
import java.time.Instant;

public class Product {

    @Getter
    private final String uuid;
    private final ProductName name;
    private final ProductDescription description;
    private ProductImage productImage;
    @Getter
    private final OnlineStoreLinks onlineStoreLinks;
    @Getter
    private final Keywords keywords;
    @Getter
    private final Instant createdDate;
    @Getter
    private final Instant lastModifiedDate;

    @Builder
    public Product(String uuid,
                   ProductName name,
                   ProductDescription description,
                   ProductImage productImage,
                   OnlineStoreLinks onlineStoreLinks,
                   Keywords keywords,
                   Instant createdDate,
                   Instant lastModifiedDate) {
        this.uuid = uuid;
        this.name = name;
        this.description = description;
        this.productImage = productImage == null ? ProductImage.empty() : productImage;
        this.onlineStoreLinks = onlineStoreLinks;
        this.keywords = keywords == null ? Keywords.EMPTY : keywords;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    public void addKeywords(Keywords keywords) {
        this.keywords.addAll(keywords);
    }

    public Path getProductImageBasePath() {
        return productImage.basePath(uuid);
    }

    public void registerProductImage(String uploadPath) {
        this.productImage = ProductImage.from(uploadPath);
    }

    public String getName() {
        return name.name();
    }

    public String getDescription() {
        return description.description();
    }

    public String getProfileImage() {
        return productImage.imagePath();
    }
}
