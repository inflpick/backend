package com.leesh.inflpick.product.port;

import com.leesh.inflpick.common.adapter.in.web.value.PageRequest;
import com.leesh.inflpick.common.core.Direction;
import com.leesh.inflpick.common.port.in.exception.InvalidPageNumberException;
import com.leesh.inflpick.common.port.in.exception.InvalidPageSizeException;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.util.Pair;

import java.util.ArrayList;
import java.util.List;

public record ProductPageQuery(@NotNull Integer page,
                               @NotNull Integer size,
                               List<Pair<ProductSortType, Direction>> sort) {

    public ProductPageQuery {
        validateUserInputs(page, size);
    }

    public static ProductPageQuery from(PageRequest request) {
        String[] sortTypes = request.sort();
        List<Pair<ProductSortType, Direction>> sort = getSortTypePairs(sortTypes);
        return new ProductPageQuery(request.page(), request.size(), sort);
    }

    private static @NotNull List<Pair<ProductSortType, Direction>> getSortTypePairs(String[] sortTypes) {
        List<Pair<ProductSortType, Direction>> sort = new ArrayList<>();
        for (String sortType : sortTypes) {
            String[] split = sortType.split(",");
            ProductSortType productSortType = ProductSortType.from(split[0]);
            Direction direction = Direction.from(split[1]);
            sort.add(Pair.of(productSortType, direction));
        }
        return sort;
    }

    private void validateUserInputs(@NotNull Integer page, @NotNull Integer size) {
        validatePage(page);
        validateSize(size);
    }

    private void validateSize(@NotNull Integer size) {
        if (size < 1 || size > 100) {
            throw new InvalidPageSizeException();
        }
    }

    private void validatePage(@NotNull Integer page) {
        if (page < 0) {
            throw new InvalidPageNumberException();
        }
    }
}
