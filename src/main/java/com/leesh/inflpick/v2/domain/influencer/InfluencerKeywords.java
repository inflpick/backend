package com.leesh.inflpick.v2.domain.influencer;

import com.leesh.inflpick.v2.domain.influencer.exception.MaximumKeywordSizeException;
import com.leesh.inflpick.v2.domain.influencer.vo.InfluencerKeywordId;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public final class InfluencerKeywords {

    private final Set<InfluencerKeywordId> ids;

    private InfluencerKeywords() {
        this.ids = new HashSet<>();
    }

    private InfluencerKeywords(Set<InfluencerKeywordId> ids) {
        this.ids = new HashSet<>(ids);
    }

    /* Business Logic */
    static InfluencerKeywords create(Set<InfluencerKeywordId> ids) {
        return new InfluencerKeywords(new HashSet<>(ids));
    }

    static InfluencerKeywords empty() {
        return new InfluencerKeywords();
    }

    Set<InfluencerKeywordId> getIds() {
        return Collections.unmodifiableSet(ids);
    }

    void add(InfluencerKeywordId id) {
        if (ids.size() > 10) {
            throw new MaximumKeywordSizeException("Keyword size cannot exceed 10, current size: " + ids.size());
        }
        ids.add(id);
    }
}
