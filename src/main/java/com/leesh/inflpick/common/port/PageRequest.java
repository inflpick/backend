package com.leesh.inflpick.common.port;

import java.util.Collection;

public interface PageRequest {

    Integer page();

    Integer size();

    Collection<SortCriterion> sortCriteria(SortableProperties sortableProperties);

}
