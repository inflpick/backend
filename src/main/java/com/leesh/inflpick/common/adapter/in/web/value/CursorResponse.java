package com.leesh.inflpick.common.adapter.in.web.value;

import com.leesh.inflpick.common.port.CursorPage;

import java.util.Collection;

public record CursorResponse<T>(Integer limit,
                                Collection<T> contents,
                                Boolean hasNext) {

    public static <T> CursorResponse<T> from(CursorPage<T> page) {
        return new CursorResponse<>(
                page.limit(),
                page.contents(),
                page.hasNext()
        );
    }
}
