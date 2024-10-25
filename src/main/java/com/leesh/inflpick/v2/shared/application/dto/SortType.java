package com.leesh.inflpick.v2.shared.application.dto;

import java.util.Collection;

public interface SortType {

    String getSortValue();

    Collection<String> availableSortFields();

}
