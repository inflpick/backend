package com.leesh.inflpick.user.v2.adapter.ui.web.dto.request;

import com.leesh.inflpick.common.port.SortDirection;
import com.leesh.inflpick.user.v2.core.dto.OffsetPageRequest;
import com.leesh.inflpick.user.v2.core.dto.SortCriterion;
import com.leesh.inflpick.user.v2.core.dto.Sortable;

import java.util.ArrayList;
import java.util.Collection;

public class WebOffsetPageRequest implements OffsetPageRequest {

    private final Integer page;
    private final Integer size;
    private final String[] sorts;

    private WebOffsetPageRequest(Integer page, Integer size, String[] sorts) {
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

    public static WebOffsetPageRequest create(Integer page, Integer size, String[] sorts) {
        return new WebOffsetPageRequest(page, size, sorts);
    }
}
