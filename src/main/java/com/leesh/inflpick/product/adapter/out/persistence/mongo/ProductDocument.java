package com.leesh.inflpick.product.adapter.out.persistence.mongo;

import com.leesh.inflpick.influencer.core.domain.value.Keywords;
import com.leesh.inflpick.product.core.domain.Product;
import com.leesh.inflpick.product.core.domain.value.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.*;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Set;

@Document(collection = "products")
public class ProductDocument implements Persistable<String> {

    @Getter
    @Id
    private final String id;
    private final String name;
    private final String description;
    private final String productImagePath;
    @Getter
    private final Set<String> keywordIds;
    private final Set<OnlineStoreLink> onlineStoreLinks;
    @CreatedBy
    private final String createdBy;
    @CreatedDate
    private final Instant createdDate;
    @LastModifiedBy
    private final String lastModifiedBy;
    @LastModifiedDate
    private final Instant lastModifiedDate;

    @Builder(access = AccessLevel.PRIVATE)
    private ProductDocument(String id,
                            String name,
                            String description,
                            String productImagePath,
                            Set<String> keywordIds,
                            Set<OnlineStoreLink> onlineStoreLinks,
                            String createdBy,
                            Instant createdDate,
                            String lastModifiedBy,
                            Instant lastModifiedDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.productImagePath = productImagePath;
        this.keywordIds = keywordIds;
        this.onlineStoreLinks = onlineStoreLinks;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.lastModifiedBy = lastModifiedBy;
        this.lastModifiedDate = lastModifiedDate;
    }

    public static ProductDocument from(Product product) {

        Set<String> keywordIds = product.getKeywords()
                .getIds();

        return ProductDocument.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .productImagePath(product.getProfileImage())
                .keywordIds(keywordIds)
                .onlineStoreLinks(product.getOnlineStoreLinks().getImmutable())
                .createdDate(product.getCreatedDate())
                .lastModifiedDate(product.getLastModifiedDate())
                .build();
    }

    public Product toEntity(Keywords keywords) {

        return Product.builder()
                .id(id)
                .name(ProductName.from(name))
                .description(ProductDescription.from(description))
                .productImage(ProductImage.from(productImagePath))
                .keywords(keywords)
                .onlineStoreLinks(OnlineStoreLinks.from(onlineStoreLinks))
                .createdBy(createdBy)
                .createdDate(createdDate)
                .lastModifiedBy(lastModifiedBy)
                .lastModifiedDate(lastModifiedDate)
                .build();
    }

    @Override
    public boolean isNew() {
        return createdDate == null;
    }
}
