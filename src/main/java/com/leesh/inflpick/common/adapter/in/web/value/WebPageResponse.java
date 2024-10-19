package com.leesh.inflpick.common.adapter.in.web.value;

import com.leesh.inflpick.common.adapter.in.web.docs.WebPageResponseApiDocs;
import com.leesh.inflpick.common.port.PageResponse;

public record WebPageResponse<T>(
        T[] contents,
        int currentPage,
        int totalPages,
        int size,
        String sorts,
        long totalElements) implements WebPageResponseApiDocs {

    public static <T> WebPageResponse<T> of(T[] contents,
                                            PageResponse<?> pageInfo) {
        return new WebPageResponse<>(contents,
                pageInfo.currentPage(),
                pageInfo.totalPages(),
                pageInfo.size(),
                pageInfo.sortProperties(),
                pageInfo.totalElements());
    }
}
