package com.leesh.inflpick.user.v2.core.dto;

import java.util.Collection;

public interface OffsetPageQuery {

    Integer getPage();

    Integer getSize();

    Collection<SortCriterion> getSortCriteria(Sortable sortable);

}
