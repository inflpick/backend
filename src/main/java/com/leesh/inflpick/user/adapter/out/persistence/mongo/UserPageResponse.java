package com.leesh.inflpick.user.adapter.out.persistence.mongo;

import com.leesh.inflpick.common.port.PageResponse;
import com.leesh.inflpick.user.v2.core.entity.User;
import lombok.Builder;

import java.util.Collection;

@Builder
public record UserPageResponse(Collection<User> contents,
                               Integer currentPage,
                               Integer totalPages,
                               Integer size,
                               Long totalElements,
                               String sortProperties) implements PageResponse<User> {
    @Override
    public Collection<User> contents() {
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
