package com.leesh.inflpick.v2.shared.application.dto;

import java.util.Collection;

public interface OffsetPageQuery {

    Integer getPage();

    Integer getSize();

    Collection<SortCriterion> getSortCriteria(Sortable sortable);

}
