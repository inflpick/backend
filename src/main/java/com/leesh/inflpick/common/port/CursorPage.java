package com.leesh.inflpick.common.port;

import java.util.Collection;

public record CursorPage<T>(
        Integer limit,
        Collection<T> contents,
        Boolean hasNext) {
}
