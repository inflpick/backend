package com.leesh.inflpick.common.port;

public record PageDetailsImpl<T>(
        int currentPage,
        int size,
        int totalPages,
        long totalElements,
        String[] sorts,
        T content) implements PageDetails<T> {

    public static <T> PageDetailsImpl<T> of(int currentPage,
                                            int size,
                                            int totalPages,
                                            long totalElements,
                                            String[] sorts,
                                            T content) {
        return new PageDetailsImpl<>(currentPage,
                size,
                totalPages,
                totalElements,
                sorts,
                content);
    }

    @Override
    public Integer getCurrentPage() {
        return currentPage;
    }

    @Override
    public Integer getPageSize() {
        return size;
    }

    @Override
    public Integer getTotalPages() {
        return totalPages;
    }

    @Override
    public Long getTotalElements() {
        return totalElements;
    }

    @Override
    public String[] getSortProperties() {
        return sorts;
    }

    @Override
    public T getContent() {
        return content;
    }
}
