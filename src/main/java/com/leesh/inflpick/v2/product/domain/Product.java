package com.leesh.inflpick.v2.product.domain;

import com.leesh.inflpick.v2.product.domain.vo.*;

import java.time.Instant;
import java.util.List;

public record Product(ProductId id,
                      ProductName name,
                      ProductDescription description,
                      ProductImage image,
                      OnlineStoreLinks onlineStoreLinks,
                      ProductKeywords keywords,
                      String createdBy,
                      Instant createdDate,
                      String lastModifiedBy,
                      Instant lastModifiedDate) {

    /* Business Logic */
    public static Product withPersistence(ProductId id,
                                          ProductName name,
                                          ProductDescription description,
                                          ProductImage image,
                                          List<OnlineStoreLink> onlineStoreLinks,
                                          List<ProductKeyword> keywords,
                                          String createdBy,
                                          Instant createdDate,
                                          String lastModifiedBy,
                                          Instant lastModifiedDate) {
        ProductKeywords productKeywords = ProductKeywords.create(keywords);
        OnlineStoreLinks links = OnlineStoreLinks.create(onlineStoreLinks);
        return new Product(id, name, description, image, links, productKeywords, createdBy, createdDate, lastModifiedBy, lastModifiedDate);
    }

    public static Product withoutPersistence(ProductName name,
                                             ProductDescription description,
                                             List<OnlineStoreLink> onlineStoreLinks) {
        ProductId emptyId = ProductId.empty();
        ProductImage emptyImage = ProductImage.empty();
        ProductKeywords productKeywords = ProductKeywords.empty();
        OnlineStoreLinks links = OnlineStoreLinks.create(onlineStoreLinks);
        return new Product(emptyId, name, description, emptyImage, links, productKeywords, "", Instant.now(), "", Instant.now());
    }

    public Product addKeywords(List<ProductKeyword> keywords) {
        ProductKeywords addedKeywords = this.keywords.addAll(keywords);
        return new Product(id, name, description, image, onlineStoreLinks, addedKeywords, createdBy, createdDate, lastModifiedBy, lastModifiedDate);
    }

    public Product update(ProductName name, ProductDescription description, List<ProductKeyword> keywords, List<OnlineStoreLink> onlineStoreLinks) {
        return withPersistence(id, name, description, image, onlineStoreLinks, keywords, createdBy, createdDate, lastModifiedBy, lastModifiedDate);
    }
}
