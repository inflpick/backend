package com.leesh.inflpick.keyword.adapter.out.persistence.mongo;

import com.leesh.inflpick.keyword.core.domain.Keyword;
import com.leesh.inflpick.keyword.core.domain.KeywordColor;
import com.leesh.inflpick.keyword.core.domain.KeywordName;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "keywords")
public class KeywordDocument implements Persistable<String> {

    @Getter
    @Id
    private final String id; // 외부에 노출할 고유한 ID
    @TextIndexed
    private final String name;
    private final String color;
    @CreatedDate
    private final Instant createdDate;
    @LastModifiedDate
    private final Instant lastModifiedDate;

    @Builder
    public KeywordDocument(String id, String name, String color, Instant createdDate, Instant lastModifiedDate) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    public static KeywordDocument from(Keyword keyword) {
        return KeywordDocument.builder()
                .id(keyword.getId())
                .name(keyword.getName())
                .color(keyword.getHexColor())
                .createdDate(keyword.getCreatedDate())
                .lastModifiedDate(keyword.getLastModifiedDate())
                .build();
    }

    public Keyword toEntity() {
        return Keyword.builder()
                .id(id)
                .name(new KeywordName(name))
                .color(KeywordColor.from(color))
                .createdDate(createdDate)
                .lastModifiedDate(lastModifiedDate)
                .build();
    }

    @Override
    public boolean isNew() {
        return createdDate == null;
    }
}
