package com.leesh.inflpick.v2.common.application.dto;

public record OffsetPageRequest(Integer page,
                                Integer size,
                                String[] sort) {

    public static OffsetPageRequest create(Integer page, Integer size, String[] sort) {
        return new OffsetPageRequest(page, size, sort);
    }
}
