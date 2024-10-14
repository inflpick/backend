package com.leesh.inflpick.common.port;

public interface PageDetails<T> {

    Integer getCurrentPage();

    Integer getPageSize();

    Integer getTotalPages();

    Long getTotalElements();

    String[] getSortProperties();

    T getContent();

}
