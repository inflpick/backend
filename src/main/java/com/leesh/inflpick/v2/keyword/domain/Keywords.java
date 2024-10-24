package com.leesh.inflpick.v2.keyword.domain;

import com.leesh.inflpick.v2.influencer.domain.exception.MaximumInfluencerKeywordSizeException;
import com.leesh.inflpick.v2.keyword.domain.vo.Keyword;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Keywords {

    private final Set<Keyword> values;

    private Keywords() {
        this.values = new HashSet<>();
    }

    private Keywords(Set<Keyword> values) {
        this.values = Collections.unmodifiableSet(values);
    }

    /* Business Logic */
    static Keywords create(Set<Keyword> ids) {
        return new Keywords(ids);
    }

    public static Keywords empty() {
        return new Keywords();
    }

    Keywords add(Keyword keyword) {
        if (values.size() + 1 > 10) {
            throw new MaximumInfluencerKeywordSizeException("Influencer Keyword size cannot exceed 10, current size: " + values.size());
        }
        HashSet<Keyword> keywords = new HashSet<>(this.values);
        keywords.add(keyword);
        return new Keywords(keywords);
    }

    public Keywords addAll(Set<Keyword> keywords) {
        if (values.size() + keywords.size() > 10) {
            throw new MaximumInfluencerKeywordSizeException("Influencer Keyword size cannot exceed 10, current size: " + values.size());
        }
        HashSet<Keyword> newKeywords = new HashSet<>(this.values);
        newKeywords.addAll(keywords);
        return new Keywords(newKeywords);
    }

    public Set<Keyword> getValues() {
        return Collections.unmodifiableSet(values);
    }

    public boolean contains(Keyword keyword) {
        return values.contains(keyword);
    }
}
