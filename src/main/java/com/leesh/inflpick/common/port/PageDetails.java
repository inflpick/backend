package com.leesh.inflpick.common.port;

public record PageDetails<T>(
        int currentPage,
        int size,
        int totalPages,
        long totalElements,
        String[] sorts,
        T content) {

    public static <T> PageDetails<T> of(int currentPage,
                                        int size,
                                        int totalPages,
                                        long totalElements,
                                        String[] sorts,
                                        T content) {
        return new PageDetails<>(currentPage,
                size,
                totalPages,
                totalElements,
                sorts,
                content);
    }
}
