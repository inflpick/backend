package com.leesh.inflpick.v2.shared.application.dto;

import lombok.Getter;

import java.util.Collection;

@Getter
public class OffsetPage<T> {

    private final Collection<T> contents;
    private final Integer page;
    private final Integer totalPages;
    private final Integer size;
    private final Long totalElements;
    private final String sortProperties;

    private OffsetPage(Collection<T> contents, Integer page, Integer totalPages, Integer size, Long totalElements, String sortProperties) {
        this.contents = contents;
        this.page = page;
        this.totalPages = totalPages;
        this.size = size;
        this.totalElements = totalElements;
        this.sortProperties = sortProperties;
    }

    public static <T> OffsetPage<T> create(Collection<T> contents, Integer page, Integer totalPages, Integer size, Long totalElements, String sortProperties) {
        return new OffsetPage<>(contents, page, totalPages, size, totalElements, sortProperties);
    }
}
