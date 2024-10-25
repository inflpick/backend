package com.leesh.inflpick.v2.keyword.adapter.out.persistence.mongo;

import com.leesh.inflpick.v2.keyword.domain.vo.Keyword;
import com.leesh.inflpick.v2.keyword.domain.vo.KeywordColor;
import com.leesh.inflpick.v2.keyword.domain.vo.KeywordId;
import com.leesh.inflpick.v2.keyword.domain.vo.KeywordName;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document(collection = "keywords")
public class KeywordDocument {

    @Id
    private final String id;
    private final String name;
    private final String hexColor;

    private KeywordDocument(String id, String name, String hexColor) {
        this.id = id;
        this.name = name;
        this.hexColor = hexColor;
    }

    static KeywordDocument from(Keyword keyword) {
        return new KeywordDocument(
                keyword.getId().getValue(),
                keyword.getName().getValue(),
                keyword.getColor().getValue());
    }

    Keyword toEntity() {
        KeywordId id = KeywordId.create(this.id);
        KeywordName keywordName = KeywordName.create(name);
        KeywordColor hexColor = KeywordColor.create(this.hexColor);
        return Keyword.builder(keywordName)
                .id(id)
                .color(hexColor)
                .build();
    }

}
