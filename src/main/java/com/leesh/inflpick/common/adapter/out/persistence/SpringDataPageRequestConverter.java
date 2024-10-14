package com.leesh.inflpick.common.adapter.out.persistence;

import com.leesh.inflpick.common.port.SortCriterion;
import com.leesh.inflpick.common.port.SortDirection;
import com.leesh.inflpick.common.port.SortableProperties;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Collection;

public class SpringDataPageRequestConverter {

    private SpringDataPageRequestConverter() {
        // Empty
    }

    private static Sort convertSortCriteria(Collection<SortCriterion> criteria) {
        Sort sortOrder = Sort.unsorted();
        for (SortCriterion sortCriterion : criteria) {
            String sortProperty = sortCriterion.sortProperty();
            SortDirection sortDirection = sortCriterion.sortDirection();
            Sort.Direction direction = sortDirection.isAscending() ? Sort.Direction.ASC : Sort.Direction.DESC;
            sortOrder = sortOrder.and(Sort.by(direction, sortProperty));
        }
        return sortOrder;
    }


    public static @NotNull PageRequest convert(com.leesh.inflpick.common.port.PageRequest request, SortableProperties sortableProperties) {
        Collection<SortCriterion> sortCriteria = request.sortCriteria(sortableProperties);
        Sort sort = SpringDataPageRequestConverter.convertSortCriteria(sortCriteria);
        return PageRequest.of(request.page(),
                request.size(),
                sort);
    }

}
