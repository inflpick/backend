package com.leesh.inflpick.v2.common.application.dto;

import com.leesh.inflpick.v2.common.application.port.SortCriterion;

import java.util.Collection;

public interface PageRequestTemp {

    Integer page();

    Integer size();

    Collection<SortCriterion> sortCriteria(SortableProperties sortableProperties);

}
