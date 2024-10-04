package com.leesh.inflpick.product.core.domain;

import com.leesh.inflpick.influencer.core.domain.value.Keywords;
import com.leesh.inflpick.product.core.domain.value.OnlineStoreLinks;
import com.leesh.inflpick.product.core.domain.value.ProductDescription;
import com.leesh.inflpick.product.core.domain.value.ProductImage;
import com.leesh.inflpick.product.core.domain.value.ProductName;
import com.leesh.inflpick.product.port.ProductCommand;
import lombok.Builder;
import lombok.Getter;

import java.nio.file.Path;
import java.time.Instant;

public class Product {

    @Getter
    private final String id;
    private ProductName name;
    private ProductDescription description;
    private ProductImage productImage;
    @Getter
    private OnlineStoreLinks onlineStoreLinks;
    @Getter
    private Keywords keywords;
    @Getter
    private final Instant createdDate;
    @Getter
    private final Instant lastModifiedDate;

    @Builder
    public Product(String id,
                   ProductName name,
                   ProductDescription description,
                   ProductImage productImage,
                   OnlineStoreLinks onlineStoreLinks,
                   Keywords keywords,
                   Instant createdDate,
                   Instant lastModifiedDate) {
        this.id = id;
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
        return productImage.basePath(id);
    }

    public void registerProductImagePath(String uploadPath) {
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

    public String getProductImage() {
        return productImage.imagePath();
    }

    public void update(ProductCommand command, Keywords keywords) {
        this.name = command.name();
        this.description = command.description();
        this.onlineStoreLinks = command.onlineStoreLinks();
        this.keywords = keywords;
    }
}
