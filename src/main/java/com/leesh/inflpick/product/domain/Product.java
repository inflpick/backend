package com.leesh.inflpick.product.domain;

import com.leesh.inflpick.keyword.domain.Keyword;
import com.leesh.inflpick.keyword.domain.Keywords;
import com.leesh.inflpick.keyword.domain.vo.KeywordId;
import com.leesh.inflpick.product.application.dto.OnlineStoreRequest;
import com.leesh.inflpick.product.application.dto.ProductRequest;
import com.leesh.inflpick.product.domain.exception.MaximumProductKeywordsException;
import com.leesh.inflpick.product.domain.vo.*;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

public final class Product {
    private final ProductId id;
    private final ProductName name;
    private final ProductDescription description;
    private final ProductImage image;
    private final OnlineStoreLinks onlineStoreLinks;
    private final Keywords keywords;
    private final String createdBy;
    private final Instant createdDate;
    private final String lastModifiedBy;
    private final Instant lastModifiedDate;

    public Product(ProductId id,
                   ProductName name,
                   ProductDescription description,
                   ProductImage image,
                   OnlineStoreLinks onlineStoreLinks,
                   Keywords keywords,
                   String createdBy,
                   Instant createdDate,
                   String lastModifiedBy,
                   Instant lastModifiedDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.onlineStoreLinks = onlineStoreLinks;
        this.keywords = keywords;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.lastModifiedBy = lastModifiedBy;
        this.lastModifiedDate = lastModifiedDate;
    }

    /* Business Logic */
    public static Product withId(ProductId id,
                                 ProductName name,
                                 ProductDescription description,
                                 ProductImage image,
                                 OnlineStoreLinks onlineStoreLinks,
                                 Keywords keywords,
                                 String createdBy,
                                 Instant createdDate,
                                 String lastModifiedBy,
                                 Instant lastModifiedDate) {
        return new Product(id, name, description, image, onlineStoreLinks, keywords, createdBy, createdDate, lastModifiedBy, lastModifiedDate);
    }

    public static Product withoutId(String name, String description, List<OnlineStoreLink> storeLinks) {
        ProductId emptyId = ProductId.empty();
        ProductName productName = ProductName.create(name);
        ProductDescription productDescription = ProductDescription.create(description);
        OnlineStoreLinks links = OnlineStoreLinks.create(storeLinks);
        ProductImage image = ProductImage.empty();
        Keywords empty = Keywords.empty();
        return new Product(emptyId,
                productName,
                productDescription,
                image,
                links,
                empty,
                null,
                null,
                null,
                null);
    }

    public Product addKeywords(List<Keyword> keywords) {
        if (this.keywords.size() + keywords.size() > 10) {
            throw new MaximumProductKeywordsException(this.keywords.size());
        }
        Keywords addedKeywords = this.keywords.add(keywords);
        return withId(id,
                name,
                description,
                image,
                onlineStoreLinks,
                addedKeywords,
                createdBy,
                createdDate,
                lastModifiedBy,
                lastModifiedDate);
    }

    public Product update(ProductRequest request) {
        ProductName name = ProductName.create(request.name());
        ProductDescription description = ProductDescription.create(request.description());
        List<OnlineStoreLink> onlineStoreLinks = request.onlineStoreLinks().stream()
                .map(OnlineStoreRequest::toEntity)
                .toList();
        return withId(id,
                name,
                description,
                image,
                OnlineStoreLinks.create(onlineStoreLinks),
                keywords,
                createdBy,
                createdDate,
                lastModifiedBy,
                Instant.now());
    }

    public Product putKeywords(List<Keyword> keywords) {
        if (keywords.size() > 10) {
            throw new MaximumProductKeywordsException(keywords.size());
        }
        List<KeywordId> keywordIds = keywords.stream()
                .map(Keyword::id)
                .toList();
        Keywords addedKeywords = this.keywords.add(keywords);
        return withId(id,
                name,
                description,
                image,
                onlineStoreLinks,
                addedKeywords,
                createdBy,
                createdDate,
                lastModifiedBy,
                Instant.now());
    }

    public Product updateImage(String imagePath) {
        ProductImage updatedImage = ProductImage.create(imagePath);
        return withId(id,
                name,
                description,
                updatedImage,
                onlineStoreLinks,
                keywords,
                createdBy,
                createdDate,
                lastModifiedBy,
                Instant.now());
    }

    public ProductId id() {
        return id;
    }

    public ProductName name() {
        return name;
    }

    public ProductDescription description() {
        return description;
    }

    public ProductImage image() {
        return image;
    }

    public OnlineStoreLinks onlineStoreLinks() {
        return onlineStoreLinks;
    }

    public Keywords keywords() {
        return keywords;
    }

    public String createdBy() {
        return createdBy;
    }

    public Instant createdDate() {
        return createdDate;
    }

    public String lastModifiedBy() {
        return lastModifiedBy;
    }

    public Instant lastModifiedDate() {
        return lastModifiedDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Product) obj;
        return Objects.equals(this.id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
