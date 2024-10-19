package com.leesh.inflpick.user.v2.core.dto;

import java.util.Collection;

public interface OffsetPageRequest {

    Integer getPage();

    Integer getSize();

    Collection<SortCriterion> getSortCriteria(Sortable sortable);

}
