package com.leesh.inflpick.keyword.domain;

import com.leesh.inflpick.keyword.domain.vo.KeywordId;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public record Keywords(List<KeywordId> ids) {

    public Keywords {
        ids = Collections.unmodifiableList(ids);
    }

    /* Business Logic */
    public static Keywords createIdString(List<String> ids) {
        List<KeywordId> keywordIds = new ArrayList<>();
        for (String id : ids) {
            KeywordId keywordId = KeywordId.create(id);
            keywordIds.add(keywordId);
        }
        return new Keywords(keywordIds);
    }

    public static Keywords create(List<KeywordId> keywordIds) {
        return new Keywords(keywordIds);
    }

    public static Keywords empty() {
        return new Keywords(new ArrayList<>());
    }

    public Integer size() {
        return ids.size();
    }

    public Keywords add(List<Keyword> keywords) {
        List<KeywordId> newIds = new ArrayList<>(ids);
        for (Keyword keyword : keywords) {
            newIds.add(keyword.id());
        }
        return new Keywords(newIds);
    }
}
