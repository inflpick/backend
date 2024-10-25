package com.leesh.inflpick.v2.shared.application.dto;

import java.util.Collection;

public record PageResponse<T>(
        Collection<T> contents,
        Integer currentPage,
        Integer totalPages,
        Integer size,
        Long totalElements,
        String sortProperties) {

}
