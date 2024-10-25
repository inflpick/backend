package com.leesh.inflpick.v2.keyword.adapter.out.persistence.mongo;

import com.leesh.inflpick.v2.influencer.domain.vo.InfluencerId;
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
    private final String influencerId;

    private KeywordDocument(String id, String name, String hexColor, String influencerId) {
        this.id = id;
        this.name = name;
        this.hexColor = hexColor;
        this.influencerId = influencerId;
    }

    static KeywordDocument from(Keyword keyword) {
        return new KeywordDocument(
                keyword.getId().getId(),
                keyword.getName().getValue(),
                keyword.getColor().getValue(),
                keyword.getInfluencerId().getValue());
    }

    Keyword toEntity() {
        KeywordId id = KeywordId.create(this.id);
        KeywordName keywordName = KeywordName.create(name);
        KeywordColor hexColor = KeywordColor.create(this.hexColor);
        InfluencerId influencerId = InfluencerId.create(this.influencerId);
        return Keyword.builder(keywordName)
                .id(id)
                .color(hexColor)
                .influencerId(influencerId)
                .build();
    }

}
