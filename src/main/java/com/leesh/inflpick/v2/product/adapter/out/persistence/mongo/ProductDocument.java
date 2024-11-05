package com.leesh.inflpick.v2.product.adapter.out.persistence.mongo;

import com.leesh.inflpick.v2.keyword.domain.vo.KeywordId;
import com.leesh.inflpick.v2.product.domain.Product;
import com.leesh.inflpick.v2.product.domain.vo.*;
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

        ProductId productId = product.id();
        ProductName productName = product.name();
        ProductDescription productDescription = product.description();
        ProductImage productImage = product.image();
        List<String> productKeywordIds = product.keywords().keywords()
                .stream()
                .map(ProductKeyword::id)
                .map(KeywordId::id)
                .toList();
        List<OnlineStoreLinkDocument> onlineStoreLinkDocuments = product.onlineStoreLinks().links()
                .stream()
                .map(OnlineStoreLinkDocument::from)
                .toList();

        return new ProductDocument(productId.id(),
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

    public Product toEntity(List<ProductKeyword> keywords) {

        ProductId productId = ProductId.create(id);
        ProductName productName = ProductName.create(name);
        ProductDescription productDescription = ProductDescription.create(description);
        ProductImage productImage = ProductImage.create(productImagePath);
        List<OnlineStoreLink> onlineStoreLinks = this.onlineStoreLinks.stream()
                .map(OnlineStoreLinkDocument::toEntity)
                .toList();
        return Product.withPersistence(productId,
                productName,
                productDescription,
                productImage,
                onlineStoreLinks,
                keywords,
                createdBy,
                createdDate,
                lastModifiedBy,
                lastModifiedDate);
    }
}
