package com.leesh.inflpick.product.adapter.out.persistence.mongo;

import com.leesh.inflpick.keyword.domain.Keywords;
import com.leesh.inflpick.keyword.domain.vo.KeywordId;
import com.leesh.inflpick.product.domain.OnlineStoreLinks;
import com.leesh.inflpick.product.domain.Product;
import com.leesh.inflpick.product.domain.vo.*;
import org.springframework.data.annotation.*;

import java.time.Instant;
import java.util.List;

public record ProductDocument(@Id String id,
                              String name,
                              String description,
                              String productImagePath,
                              List<String> keywordIds,
                              List<OnlineStoreLinkDocument> onlineStoreLinks,
                              @CreatedBy String createdBy,
                              @CreatedDate Instant createdDate,
                              @LastModifiedBy String lastModifiedBy,
                              @LastModifiedDate Instant lastModifiedDate) {

    public static ProductDocument from(Product product) {

        String id = product.id().isEmpty() ? null : product.id().id();
        ProductName productName = product.name();
        ProductDescription productDescription = product.description();
        ProductImage productImage = product.image();
        List<String> productKeywordIds = product.keywords().ids()
                .stream()
                .map(KeywordId::id)
                .toList();
        List<OnlineStoreLinkDocument> onlineStoreLinkDocuments = product.onlineStoreLinks().links()
                .stream()
                .map(OnlineStoreLinkDocument::from)
                .toList();

        return new ProductDocument(id,
                productName.name(),
                productDescription.description(),
                productImage.path(),
                productKeywordIds,
                onlineStoreLinkDocuments,
                product.createdBy(),
                product.createdDate(),
                product.lastModifiedBy(),
                product.lastModifiedDate());
    }

    public Product toEntity() {

        ProductId productId = ProductId.create(id);
        ProductName productName = ProductName.create(name);
        ProductDescription productDescription = ProductDescription.create(description);
        ProductImage productImage = ProductImage.create(productImagePath);
        List<OnlineStoreLink> onlineStoreLinks = this.onlineStoreLinks.stream()
                .map(OnlineStoreLinkDocument::toEntity)
                .toList();
        OnlineStoreLinks links = OnlineStoreLinks.create(onlineStoreLinks);
        return Product.withId(productId,
                productName,
                productDescription,
                productImage,
                links,
                Keywords.createIdString(keywordIds),
                createdBy,
                createdDate,
                lastModifiedBy,
                lastModifiedDate);
    }
}
