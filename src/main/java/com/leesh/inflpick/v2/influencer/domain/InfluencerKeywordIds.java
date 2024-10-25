package com.leesh.inflpick.v2.influencer.domain;

import com.leesh.inflpick.v2.influencer.domain.exception.MaximumInfluencerKeywordSizeException;
import com.leesh.inflpick.v2.keyword.domain.vo.KeywordId;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
public class InfluencerKeywordIds {

    private final Set<KeywordId> values;

    private InfluencerKeywordIds() {
        this.values = new HashSet<>();
    }

    private InfluencerKeywordIds(Set<KeywordId> values) {
        this.values = values;
    }

    /* Business Logic */
    static InfluencerKeywordIds empty() {
        return new InfluencerKeywordIds();
    }

    static InfluencerKeywordIds create(Set<KeywordId> values) {
        return new InfluencerKeywordIds(values);
    }

    InfluencerKeywordIds addAll(Set<KeywordId> keywordIds) {
        if (values.size() + keywordIds.size() > 10) {
            throw new MaximumInfluencerKeywordSizeException("Influencer Keyword size cannot exceed 10, current size: " + values.size());
        }
        Set<KeywordId> newKeywordIds = new HashSet<>(values);
        newKeywordIds.addAll(keywordIds);
        return new InfluencerKeywordIds(newKeywordIds);
    }
}
