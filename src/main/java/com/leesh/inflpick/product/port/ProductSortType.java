package com.leesh.inflpick.product.port;

import com.leesh.inflpick.common.port.SortDirection;
import com.leesh.inflpick.common.port.SortType;
import com.leesh.inflpick.product.core.domain.exception.InvalidProductSortTypeException;
import org.springframework.data.util.Pair;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public enum ProductSortType implements SortType {

    NAME("name"),
    CREATED_DATE("createdDate"),
    LAST_MODIFIED_DATE("lastModifiedDate"),
    ;

    private final String value;

    ProductSortType(String value) {
        this.value = value;
    }

    public static List<String> availableSortTypes() {
        return Arrays.stream(ProductSortType.values())
                .map(ProductSortType::getSortValue)
                .collect(Collectors.toList());
    }

    private static ProductSortType findMatchTypeOrThrows(String sort) {
        ProductSortType[] values = ProductSortType.values();
        for (ProductSortType value : values) {
            if (value.getSortValue().equals(sort)) {
                return value;
            }
        }
        throw new InvalidProductSortTypeException(sort);
    }

    public static Collection<Pair<SortType, SortDirection>> toSortPairs(String[] sortTypes) {
        return Arrays.stream(sortTypes)
                .map(sortType -> {
                    String[] split = sortType.split(",");
                    SortType productSortType = ProductSortType.findMatchTypeOrThrows(split[0]);
                    SortDirection sortDirection = SortDirection.findMatchTypeOrThrows(split[1]);
                    return Pair.of(productSortType, sortDirection);
                })
                .collect(Collectors.toList());
    }

    @Override
    public String getSortValue() {
        return value;
    }
}
