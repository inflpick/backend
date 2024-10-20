package com.leesh.inflpick.v2.adapter.in.web.common;

import com.leesh.inflpick.common.port.SortDirection;
import com.leesh.inflpick.v2.domain.common.dto.OffsetPageQuery;
import com.leesh.inflpick.v2.domain.common.dto.SortCriterion;
import com.leesh.inflpick.v2.domain.common.dto.Sortable;

import java.util.ArrayList;
import java.util.Collection;

public class WebOffsetPageQuery implements OffsetPageQuery {

    private final Integer page;
    private final Integer size;
    private final String[] sorts;

    private WebOffsetPageQuery(Integer page, Integer size, String[] sorts) {
        this.page = page;
        this.size = size;
        this.sorts = sorts;
    }

    @Override
    public Integer getPage() {
        return page;
    }

    @Override
    public Integer getSize() {
        return size;
    }

    @Override
    public Collection<SortCriterion> getSortCriteria(Sortable sortable) {

        Collection<SortCriterion> sortCriteria = new ArrayList<>();
        Collection<String> sortFields = sortable.getProperties();
        Collection<String> sortDirections = SortDirection.availableDirectionsValues();

        for (String sort : this.sorts) {
            String[] split = sort.split(",");
            if (split.length == 2) {
                String sortField = split[0];
                String sortDirection = split[1];
                if (sortFields.contains(sortField) && sortDirections.contains(sortDirection)) {
                    sortCriteria.add(
                            new SortCriterion() {
                                @Override
                                public String sortProperty() {
                                    return sortField;
                                }
                                @Override
                                public SortDirection sortDirection() {
                                    String upperCase = sortDirection.toUpperCase();
                                    return SortDirection.valueOf(upperCase);
                                }
                            }
                    );
                }
            }
        }

        return sortCriteria;
    }

    public static WebOffsetPageQuery create(Integer page, Integer size, String[] sorts) {
        return new WebOffsetPageQuery(page, size, sorts);
    }
}
