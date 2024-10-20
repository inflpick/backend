package com.leesh.inflpick.v2.appilcation.dto.common;

import com.leesh.inflpick.common.port.SortDirection;

public interface SortCriterion {

    String sortProperty();

    SortDirection sortDirection();

}
