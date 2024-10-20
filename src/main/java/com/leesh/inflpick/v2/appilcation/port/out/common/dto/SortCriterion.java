package com.leesh.inflpick.v2.appilcation.port.out.common.dto;

import com.leesh.inflpick.common.port.SortDirection;

public interface SortCriterion {

    String sortProperty();

    SortDirection sortDirection();

}
