package com.leesh.inflpick.common.adapter.out.persistence;

import com.leesh.inflpick.common.core.Direction;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.Pair;

import java.util.List;

public class SortTypeHelper {

    private Sort getSortCriteria(List<Pair<SortType, Direction>> sortPairs) {
        Sort sortOrder = Sort.unsorted();
        for (Pair<SortType, Direction> sortPair : sortPairs) {
            SortType sortType = sortPair.getFirst();
            String sortField = sortType.getValue();
            Direction direction = sortPair.getSecond();
            Sort.Direction sortDirection = direction.isDescending() ? Sort.Direction.DESC : Sort.Direction.ASC;
            sortOrder = sortOrder.and(Sort.by(sortDirection, sortField));
        }
        return sortOrder;
    }
}
