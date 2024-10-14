package com.leesh.inflpick.common.port;

import java.util.Collection;

public interface PageResponse<T> {

    Collection<T> contents();

    Integer currentPage();

    Integer totalPages();

    Integer size();

    Long totalElements();

    String[] sortProperties();

}
