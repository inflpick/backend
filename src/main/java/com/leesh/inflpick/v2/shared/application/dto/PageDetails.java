package com.leesh.inflpick.v2.shared.application.dto;

public interface PageDetails<T> {

    Integer getCurrentPage();

    Integer getPageSize();

    Integer getTotalPages();

    Long getTotalElements();

    String[] getSortProperties();

    T getContent();

}
