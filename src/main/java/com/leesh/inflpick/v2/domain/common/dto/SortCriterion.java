package com.leesh.inflpick.v2.domain.common.dto;

import com.leesh.inflpick.common.port.SortDirection;

public interface SortCriterion {

    String sortProperty();

    SortDirection sortDirection();

}
