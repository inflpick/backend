package com.leesh.inflpick.common.application.dto;

public record PageRequest(Integer page,
                          Integer size,
                          String[] sort) {

    public static PageRequest create(Integer page, Integer size, String[] sort) {
        return new PageRequest(page, size, sort);
    }
}
