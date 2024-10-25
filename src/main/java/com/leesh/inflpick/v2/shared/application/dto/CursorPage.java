package com.leesh.inflpick.v2.shared.application.dto;

import java.util.Collection;

public record CursorPage<T>(
        Integer limit,
        Collection<T> contents,
        Boolean hasNext) {
}
