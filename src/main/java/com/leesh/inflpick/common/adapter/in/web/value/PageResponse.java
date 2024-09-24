package com.leesh.inflpick.common.adapter.in.web.value;

public record PageResponse<T>(T contents,
                               int currentPage,
                               int totalPages,
                               int size,
                               String[] sorts,
                               long totalElements) {
}
