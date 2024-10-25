package com.leesh.inflpick.v2.keyword.domain;

import com.leesh.inflpick.v2.keyword.domain.vo.KeywordId;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class Keywords {

    private final List<KeywordId> ids;

    private Keywords() {
        this.ids = Collections.unmodifiableList(new ArrayList<>());
    }

    private Keywords(List<KeywordId> ids) {
        this.ids = Collections.unmodifiableList(ids);
    }

    /* Business Logic */
    public static Keywords create(List<KeywordId> ids) {
        return new Keywords(ids);
    }

    public static Keywords empty() {
        return new Keywords();
    }

    public Integer size() {
        return ids.size();
    }
}
