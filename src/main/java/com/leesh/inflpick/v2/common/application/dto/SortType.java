package com.leesh.inflpick.v2.common.application.dto;

import java.util.Collection;

public interface SortType {

    String getSortValue();

    Collection<String> availableSortFields();

}
