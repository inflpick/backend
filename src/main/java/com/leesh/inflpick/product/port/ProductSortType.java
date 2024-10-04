package com.leesh.inflpick.product.port;

import com.leesh.inflpick.product.core.domain.exception.InvalidProductSortTypeException;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public enum ProductSortType {

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
                .map(ProductSortType::getValue)
                .collect(Collectors.toList());
    }

    public static ProductSortType from(String sort) {
        return findOrThrowMatchType(sort);
    }

    private static ProductSortType findOrThrowMatchType(String sort) {
        ProductSortType[] values = ProductSortType.values();
        for (ProductSortType value : values) {
            if (value.getValue().equals(sort)) {
                return value;
            }
        }
        throw new InvalidProductSortTypeException(sort);
    }

}
