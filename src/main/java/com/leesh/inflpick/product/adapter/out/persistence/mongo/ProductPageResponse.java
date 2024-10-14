package com.leesh.inflpick.product.adapter.out.persistence.mongo;

import com.leesh.inflpick.common.port.PageResponse;
import com.leesh.inflpick.product.core.domain.Product;
import lombok.Builder;

import java.util.Collection;

@Builder
public record ProductPageResponse(Collection<Product> contents,
                                   Integer currentPage,
                                   Integer totalPages,
                                   Integer size,
                                   Long totalElements,
                                   String sortProperties) implements PageResponse<Product> {
    @Override
    public Collection<Product> contents() {
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
