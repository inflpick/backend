package com.leesh.inflpick.v2.common.application.dto;

import com.leesh.inflpick.v2.common.adapter.out.docs.swagger.CursorResponseDocs;

import java.util.Collection;

public record CursorResponse<T>(Integer limit,
                                Collection<T> contents,
                                Boolean hasNext) implements CursorResponseDocs {
}
