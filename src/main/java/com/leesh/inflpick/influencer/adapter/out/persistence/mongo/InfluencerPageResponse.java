package com.leesh.inflpick.influencer.adapter.out.persistence.mongo;

import com.leesh.inflpick.common.port.PageResponse;
import com.leesh.inflpick.influencer.core.domain.Influencer;
import lombok.Builder;

import java.util.Collection;

@Builder
public record InfluencerPageResponse(Collection<Influencer> contents,
                                     Integer currentPage,
                                     Integer totalPages,
                                     Integer size,
                                     Long totalElements,
                                     String sortProperties) implements PageResponse<Influencer> {

    @Override
    public Collection<Influencer> contents() {
        return contents;
    }

    @Override
    public Integer currentPage() {
        return currentPage;
    }

    @Override
    public Integer totalPages() {
        return totalPages;
    }

    @Override
    public Integer size() {
        return size;
    }

    @Override
    public Long totalElements() {
        return totalElements;
    }

    @Override
    public String sortProperties() {
        return sortProperties;
    }
}
