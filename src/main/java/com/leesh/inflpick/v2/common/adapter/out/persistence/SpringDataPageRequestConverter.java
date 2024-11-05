package com.leesh.inflpick.v2.common.adapter.out.persistence;

import com.leesh.inflpick.v2.common.application.dto.*;
import com.leesh.inflpick.v2.common.application.port.SortCriterion;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Collection;

public class SpringDataPageRequestConverter {

    private SpringDataPageRequestConverter() {
        // Utility class
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


    public static @NotNull PageRequest convert(PageRequestTemp request, SortableProperties sortableProperties) {
        Collection<SortCriterion> sortCriteria = request.sortCriteria(sortableProperties);
        Sort sort = SpringDataPageRequestConverter.convertSortCriteria(sortCriteria);
        return PageRequest.of(request.page(),
                request.size(),
                sort);
    }

    public static PageRequest convert(OffsetPageRequest request, Sortable sortable) {
        Collection<String> sortableFields = sortable.getProperties();
        Sort sortOrder = Sort.unsorted();
        for (String sort : request.sort()) {
            String[] split = sort.split(",");
            if (split.length == 2) {
                String sortField = split[0];
                String sortDirection = split[1];
                if (sortableFields.contains(sortField)) {
                    Sort.Direction direction = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.toString()) ? Sort.Direction.ASC : Sort.Direction.DESC;
                    sortOrder = sortOrder.and(Sort.by(direction, sortField));
                }
            }
        }
        return PageRequest.of(request.page(),
                request.size(),
                sortOrder);
    }

}
