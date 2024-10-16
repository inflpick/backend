package com.leesh.inflpick.common.port;

import java.util.Collection;

public interface SortType {

    String getSortValue();

    Collection<String> availableSortFields();

}
