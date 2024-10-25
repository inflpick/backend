package com.leesh.inflpick.v2.shared.adapter.in.web;

import com.leesh.inflpick.common.adapter.in.web.docs.PageWebResponseDocs;
import com.leesh.inflpick.v2.shared.application.dto.PageResponse;

public record PageWebResponse<T>(
        T[] contents,
        int currentPage,
        int totalPages,
        int size,
        String sorts,
        long totalElements) implements PageWebResponseDocs {

    public static <T> PageWebResponse<T> of(T[] contents,
                                            PageResponse<?> pageInfo) {
        return new PageWebResponse<>(contents,
                pageInfo.currentPage(),
                pageInfo.totalPages(),
                pageInfo.size(),
                pageInfo.sortProperties(),
                pageInfo.totalElements());
    }
}
