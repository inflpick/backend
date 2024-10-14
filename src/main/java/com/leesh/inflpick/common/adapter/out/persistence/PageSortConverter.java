package com.leesh.inflpick.common.adapter.out.persistence;

import com.leesh.inflpick.common.port.SortCriterion;
import com.leesh.inflpick.common.port.SortDirection;
import org.springframework.data.domain.Sort;

import java.util.Collection;

public class PageSortConverter {

    private PageSortConverter() {
        // Empty
    }

    public static Sort convertSortCriteria(Collection<SortCriterion> criteria) {
        Sort sortOrder = Sort.unsorted();
        for (SortCriterion sortCriterion : criteria) {
            String sortProperty = sortCriterion.sortProperty();
            SortDirection sortDirection = sortCriterion.sortDirection();
            Sort.Direction direction = sortDirection.isAscending() ? Sort.Direction.ASC : Sort.Direction.DESC;
            sortOrder = sortOrder.and(Sort.by(direction, sortProperty));
        }
        return sortOrder;
    }

    public static String[] convertSortProperties(Sort sort) {
        return sort.isSorted() ?
                sort.toString().split(",") :
                Sort.unsorted().toString().split(",");
    }

}
