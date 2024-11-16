package com.leesh.inflpick.common.adapter.out.persistence;

import com.leesh.inflpick.common.application.dto.PageRequest;
import com.leesh.inflpick.common.application.dto.Sortable;
import org.springframework.data.domain.Sort;

import java.util.Collection;

public class SpringDataPageRequestConverter {

    private SpringDataPageRequestConverter() {
        // Utility class
    }

    public static org.springframework.data.domain.PageRequest convert(PageRequest request, Sortable sortable) {
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
        return org.springframework.data.domain.PageRequest.of(request.page(),
                request.size(),
                sortOrder);
    }

}
