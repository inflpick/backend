package com.leesh.inflpick.product.port;

import com.leesh.inflpick.common.core.Direction;
import com.leesh.inflpick.product.core.domain.exception.InvalidProductSortTypeException;
import lombok.Getter;
import org.springframework.data.util.Pair;

import java.util.Arrays;
import java.util.Collection;
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

    private static ProductSortType findMatchTypeOrThrows(String sort) {
        ProductSortType[] values = ProductSortType.values();
        for (ProductSortType value : values) {
            if (value.getValue().equals(sort)) {
                return value;
            }
        }
        throw new InvalidProductSortTypeException(sort);
    }

    public static Collection<Pair<ProductSortType, Direction>> toSortPairs(String[] sortTypes) {
        return Arrays.stream(sortTypes)
                .map(sortType -> {
                    String[] split = sortType.split(",");
                    ProductSortType productSortType = ProductSortType.findMatchTypeOrThrows(split[0]);
                    Direction direction = Direction.findMatchTypeOrThrows(split[1]);
                    return Pair.of(productSortType, direction);
                })
                .collect(Collectors.toList());
    }

}
