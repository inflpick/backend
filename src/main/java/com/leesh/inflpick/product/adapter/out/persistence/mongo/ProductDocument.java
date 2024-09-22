package com.leesh.inflpick.product.adapter.out.persistence.mongo;

import com.leesh.inflpick.influencer.core.domain.value.Keywords;
import com.leesh.inflpick.product.core.Product;
import com.leesh.inflpick.product.core.value.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Set;

@Document(collection = "products")
public class ProductDocument {

    @Getter
    private final String uuid;
    private final String name;
    private final String description;
    private final String productImagePath;
    @Getter
    private final Set<String> keywordUuids;
    private final Set<OnlineStoreLink> onlineStoreLinks;
    @CreatedBy
    private final String createdBy;
    @CreatedDate
    private final Instant createdDate;
    @LastModifiedBy
    private final String lastModifiedBy;
    @LastModifiedDate
    private final Instant lastModifiedDate;
    @Version
    private final Long version;

    @Builder(access = AccessLevel.PRIVATE)
    private ProductDocument(String uuid,
                            String name,
                            String description,
                            String productImagePath,
                            Set<String> keywordUuids,
                            Set<OnlineStoreLink> onlineStoreLinks,
                            String createdBy,
                            Instant createdDate,
                            String lastModifiedBy,
                            Instant lastModifiedDate,
                            Long version) {
        this.uuid = uuid;
        this.name = name;
        this.description = description;
        this.productImagePath = productImagePath;
        this.keywordUuids = keywordUuids;
        this.onlineStoreLinks = onlineStoreLinks;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.lastModifiedBy = lastModifiedBy;
        this.lastModifiedDate = lastModifiedDate;
        this.version = version;
    }

    public static ProductDocument from(Product product) {

        Set<String> keywordUuids = product.getKeywords()
                .getUuids();

        return ProductDocument.builder()
                .uuid(product.getUuid())
                .name(product.getName())
                .description(product.getDescription())
                .productImagePath(product.getProfileImage())
                .keywordUuids(keywordUuids)
                .onlineStoreLinks(product.getOnlineStoreLinks().getImmutable())
                .build();
    }

    public Product toEntity(Keywords keywords) {

        return Product.builder()
                .uuid(uuid)
                .name(ProductName.from(name))
                .description(ProductDescription.from(description))
                .productImage(ProductImage.from(productImagePath))
                .keywords(keywords)
                .onlineStoreLinks(OnlineStoreLinks.from(onlineStoreLinks))
                .createdDate(createdDate)
                .lastModifiedDate(lastModifiedDate)
                .build();
    }
}
