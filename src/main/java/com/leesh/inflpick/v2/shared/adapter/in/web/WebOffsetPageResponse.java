package com.leesh.inflpick.v2.shared.adapter.in.web;


import com.leesh.inflpick.common.adapter.in.web.value.WebPageResponse;
import com.leesh.inflpick.common.port.PageResponse;
import com.leesh.inflpick.v2.shared.application.dto.OffsetPage;
import lombok.Getter;

@Getter
public class WebOffsetPageResponse<T> {

    private final T[] contents;
    private final int currentPage;
    private final int totalPages;
    private final int size;
    private final String sorts;
    private final long totalElements;

    private WebOffsetPageResponse(
            T[] contents,
            int currentPage,
            int totalPages,
            int size,
            String sorts,
            long totalElements) {
        this.contents = contents;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.size = size;
        this.sorts = sorts;
        this.totalElements = totalElements;
    }

    public static <T> WebPageResponse<T> of(T[] contents,
                                            PageResponse<?> pageInfo) {
        return new WebPageResponse<>(contents,
                pageInfo.currentPage(),
                pageInfo.totalPages(),
                pageInfo.size(),
                pageInfo.sortProperties(),
                pageInfo.totalElements());
    }

    public static <T> WebOffsetPageResponse<T> create(T[] contents, OffsetPage<?> offsetPage) {
        return new WebOffsetPageResponse<>(
                contents,
                offsetPage.getPage(),
                offsetPage.getTotalPages(),
                offsetPage.getSize(),
                offsetPage.getSortProperties(),
                offsetPage.getTotalElements());
    }

}
