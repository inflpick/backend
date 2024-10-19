package com.leesh.inflpick.user.v2.core.dto;

import com.leesh.inflpick.common.port.SortDirection;

public interface SortCriterion {

    String sortProperty();

    SortDirection sortDirection();

}
