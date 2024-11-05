package com.leesh.inflpick.v2.common.application.dto;

public interface PageDetails<T> {

    Integer getCurrentPage();

    Integer getPageSize();

    Integer getTotalPages();

    Long getTotalElements();

    String[] getSortProperties();

    T getContent();

}
