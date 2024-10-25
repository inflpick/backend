package com.leesh.inflpick.v2.shared.application.dto;

import com.leesh.inflpick.v2.shared.application.port.SortCriterion;

import java.util.Collection;

public interface PageRequest {

    Integer page();

    Integer size();

    Collection<SortCriterion> sortCriteria(SortableProperties sortableProperties);

}
