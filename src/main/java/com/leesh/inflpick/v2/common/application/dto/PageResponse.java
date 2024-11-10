package com.leesh.inflpick.v2.common.application.dto;

import com.leesh.inflpick.v2.common.adapter.out.docs.swagger.PageResponseDocs;

import java.util.Collection;

public record PageResponse<T>(
        Collection<T> contents,
        Integer currentPage,
        Integer totalPages,
        Integer size,
        Long totalElements,
        String sortProperties) implements PageResponseDocs {

    public static <T> PageResponse<T> create(Collection<T> contents, Integer currentPage, Integer totalPages, Integer size, Long totalElements, String sortProperties) {
        return new PageResponse<>(contents, currentPage, totalPages, size, totalElements, sortProperties);
    }
}
