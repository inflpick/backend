package com.leesh.inflpick.keyword.adapter.out.persistence.mongo;

import com.leesh.inflpick.keyword.core.domain.Keyword;
import com.leesh.inflpick.keyword.core.domain.KeywordColor;
import com.leesh.inflpick.keyword.core.domain.KeywordName;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "keywords")
public class KeywordDocument {

    @Getter
    private final String uuid; // 외부에 노출할 고유한 ID
    @TextIndexed
    private final String name;
    private final String color;
    @Version
    private final Long version;

    @Builder
    public KeywordDocument(String uuid, String name, String color, Long version) {
        this.uuid = uuid;
        this.name = name;
        this.color = color;
        this.version = version;
    }

    public static KeywordDocument from(Keyword keyword) {
        return KeywordDocument.builder()
                .uuid(keyword.getUuid())
                .name(keyword.getName())
                .color(keyword.getHexColor())
                .build();
    }

    public Keyword toEntity() {
        return Keyword.builder()
                .uuid(uuid)
                .name(new KeywordName(name))
                .color(KeywordColor.from(color))
                .build();
    }
}
