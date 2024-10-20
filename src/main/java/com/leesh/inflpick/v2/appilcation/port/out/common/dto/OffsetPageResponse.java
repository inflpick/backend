package com.leesh.inflpick.v2.appilcation.port.out.common.dto;

import lombok.Getter;

import java.util.Collection;

@Getter
public class OffsetPageResponse<T> {

    private final Collection<T> contents;
    private final Integer page;
    private final Integer totalPages;
    private final Integer size;
    private final Long totalElements;
    private final String sortProperties;

    private OffsetPageResponse(Collection<T> contents, Integer page, Integer totalPages, Integer size, Long totalElements, String sortProperties) {
        this.contents = contents;
        this.page = page;
        this.totalPages = totalPages;
        this.size = size;
        this.totalElements = totalElements;
        this.sortProperties = sortProperties;
    }

    public static <T> OffsetPageResponse<T> create(Collection<T> contents, Integer page, Integer totalPages, Integer size, Long totalElements, String sortProperties) {
        return new OffsetPageResponse<>(contents, page, totalPages, size, totalElements, sortProperties);
    }
}
