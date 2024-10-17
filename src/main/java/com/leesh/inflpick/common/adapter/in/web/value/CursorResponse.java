package com.leesh.inflpick.common.adapter.in.web.value;

import com.leesh.inflpick.common.adapter.in.web.docs.CursorResponseApiDocs;

import java.util.Collection;

public record CursorResponse<T>(
        Integer limit,
        Collection<T> contents,
        Boolean hasNext) implements CursorResponseApiDocs<T> {
}
