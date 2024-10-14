package com.leesh.inflpick.common.adapter.in.web;

import com.leesh.inflpick.common.adapter.in.web.exception.InvalidSortParameterException;
import com.leesh.inflpick.common.port.PageRequest;
import com.leesh.inflpick.common.port.SortCriterion;
import com.leesh.inflpick.common.port.SortDirection;
import com.leesh.inflpick.common.port.SortableProperties;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public record WebOffsetPageRequest(
        @Schema(description = "페이지 번호 (0부터 시작)", example = "0", defaultValue = "0", implementation = Integer.class, requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        Integer page,
        @Schema(description = "페이지 크기", example = "20", defaultValue = "20", implementation = Integer.class, requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        Integer size,
        @ArraySchema(schema = @Schema(description = "정렬 기준", example = "createdDate,asc", defaultValue = "createdDate,asc", implementation = String.class, requiredMode = Schema.RequiredMode.NOT_REQUIRED))
        String[] sort) implements PageRequest {

    private static final int DEFAULT_PAGE = 0;
    private static final int DEFAULT_SIZE = 20;
    private static final String[] DEFAULT_SORT = {"createdDate,asc"};

    public WebOffsetPageRequest(@Nullable Integer page,
                                @Nullable Integer size,
                                @Nullable String[] sort) {
        this.page = Objects.requireNonNullElse(page, DEFAULT_PAGE);
        this.size = Objects.requireNonNullElse(size, DEFAULT_SIZE);
        this.sort = Objects.requireNonNullElse(sort, DEFAULT_SORT);
        validateSort();
    }

    private void validateSort() {
        for (String sortDirection : this.sort) {
            if (sortDirection.split(",").length != 2) {
                throw new InvalidSortParameterException();
            }
        }
    }

    @Override
    public Collection<SortCriterion> sortCriteria(SortableProperties sortableProperties) {

        Collection<SortCriterion> sortCriteria = new ArrayList<>();
        Collection<String> sortFields = sortableProperties.availableValues();
        Collection<String> sortDirections = SortDirection.availableDirections();

        for (String sort : this.sort) {
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
}
