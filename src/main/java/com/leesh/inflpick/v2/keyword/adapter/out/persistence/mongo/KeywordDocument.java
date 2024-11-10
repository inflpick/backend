package com.leesh.inflpick.v2.keyword.adapter.out.persistence.mongo;

import com.leesh.inflpick.v2.keyword.domain.Keyword;
import com.leesh.inflpick.v2.keyword.domain.vo.KeywordColor;
import com.leesh.inflpick.v2.keyword.domain.vo.KeywordId;
import com.leesh.inflpick.v2.keyword.domain.vo.KeywordName;
import com.leesh.inflpick.v2.product.domain.vo.ProductKeyword;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "keywords")
public record KeywordDocument(@Id String id,
                              String name,
                              String color,
                              @CreatedDate Instant createdDate,
                              @CreatedBy String createdBy,
                              @LastModifiedDate Instant lastModifiedDate,
                              @LastModifiedBy String lastModifiedBy) {

    public static KeywordDocument from(Keyword keyword) {
        String id = keyword.id().isEmpty() ? null : keyword.id().id();
        return new KeywordDocument(
                id,
                keyword.name().name(),
                keyword.color().hexColor(),
                null,
                null,
                null,
                null);
    }

    public Keyword toEntity() {
        KeywordId id = KeywordId.create(this.id);
        KeywordName keywordName = KeywordName.create(name);
        KeywordColor hexColor = KeywordColor.create(this.color);
        return new Keyword(id, keywordName, hexColor, createdDate, createdBy, lastModifiedDate, lastModifiedBy);
    }

    public ProductKeyword toProductKeyword() {
        KeywordId id = KeywordId.create(this.id);
        KeywordName keywordName = KeywordName.create(name);
        KeywordColor hexColor = KeywordColor.create(this.color);
        return ProductKeyword.create(id, keywordName, hexColor);
    }
}
