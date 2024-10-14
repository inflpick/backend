package com.leesh.inflpick.common.adapter.out.persistence;

import com.leesh.inflpick.common.core.Direction;
import com.leesh.inflpick.common.port.SortType;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.Pair;

import java.util.Collection;

public class PageSortConverter {

    private PageSortConverter() {
        // Empty
    }

    public static Sort convertSortCriteria(Collection<Pair<SortType, Direction>> sortPairs) {
        Sort sortOrder = Sort.unsorted();
        for (Pair<SortType, Direction> sortPair : sortPairs) {
            SortType sortType = sortPair.getFirst();
            String sortField = sortType.getSortValue();
            Direction direction = sortPair.getSecond();
            Sort.Direction sortDirection = direction.isDescending() ? Sort.Direction.DESC : Sort.Direction.ASC;
            sortOrder = sortOrder.and(Sort.by(sortDirection, sortField));
        }
        return sortOrder;
    }

    public static String[] convertSortProperties(Sort sort) {
        return sort.isSorted() ?
                sort.toString().split(",") :
                Sort.unsorted().toString().split(",");
    }

}
