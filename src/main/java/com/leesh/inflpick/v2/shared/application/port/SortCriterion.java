package com.leesh.inflpick.v2.shared.application.port;

import com.leesh.inflpick.v2.shared.application.dto.SortDirection;

public interface SortCriterion {

    String sortProperty();

    SortDirection sortDirection();
}
