package com.leesh.inflpick.common.port;

public record PageDetails<T>(
        int currentPage,
        int size,
        int totalPages,
        long totalElements,
        String[] sorts,
        T content) {
}
