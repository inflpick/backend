package com.leesh.inflpick.v2.common.application.port;

import com.leesh.inflpick.v2.common.application.dto.SortDirection;

public interface SortCriterion {

    String sortProperty();

    SortDirection sortDirection();
}
