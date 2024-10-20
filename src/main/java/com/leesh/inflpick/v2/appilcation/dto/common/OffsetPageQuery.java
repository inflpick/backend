package com.leesh.inflpick.v2.appilcation.dto.common;

import java.util.Collection;

public interface OffsetPageQuery {

    Integer getPage();

    Integer getSize();

    Collection<SortCriterion> getSortCriteria(Sortable sortable);

}
