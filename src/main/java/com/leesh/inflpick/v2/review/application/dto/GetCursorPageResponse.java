package com.leesh.inflpick.v2.review.application.dto;

import java.util.Collection;

public record GetCursorPageResponse<T>(Integer limit,
                                       Collection<T> contents,
                                       Boolean hasNext) {
}
