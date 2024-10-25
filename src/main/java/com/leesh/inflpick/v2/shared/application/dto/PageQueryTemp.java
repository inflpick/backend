package com.leesh.inflpick.v2.shared.application.dto;

import org.springframework.data.util.Pair;

import java.util.Collection;

public record PageQueryTemp(Integer page,
                            Integer size,
                            Collection<Pair<SortType, SortDirection>> sortPairs) {

    public static PageQueryTemp of(Integer page,
                                   Integer size,
                                   Collection<Pair<SortType, SortDirection>> sortPairs) {
        return new PageQueryTemp(page, size, sortPairs);
    }

}
