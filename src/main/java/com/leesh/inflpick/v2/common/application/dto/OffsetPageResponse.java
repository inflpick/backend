package com.leesh.inflpick.v2.common.application.dto;

import java.util.Collection;

public record OffsetPageResponse<T>(
        Collection<T> contents,
        Integer currentPage,
        Integer totalPages,
        Integer size,
        Long totalElements,
        String sortProperties) implements OffsetPageResponseDocs {

    public static <T> OffsetPageResponse<T> create(Collection<T> contents, Integer currentPage, Integer totalPages, Integer size, Long totalElements, String sortProperties) {
        return new OffsetPageResponse<>(contents, currentPage, totalPages, size, totalElements, sortProperties);
    }
}
