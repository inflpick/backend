package com.leesh.inflpick.common.core;

import com.leesh.inflpick.common.adapter.in.web.value.PageRequest;
import com.leesh.inflpick.common.port.in.exception.InvalidPageNumberException;
import com.leesh.inflpick.common.port.in.exception.InvalidPageSizeException;
import com.leesh.inflpick.influencer.core.domain.value.InfluencerSortType;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.util.Pair;

import java.util.ArrayList;
import java.util.List;

public record PageQuery(@NotNull Integer page,
                        @NotNull Integer size,
                        List<Pair<InfluencerSortType, Direction>> sort) {

    public PageQuery {
        validateUserInputs(page, size);
    }

    public static PageQuery from(PageRequest request) {
        String[] sortTypes = request.sort();
        List<Pair<InfluencerSortType, Direction>> sort = getSortTypePairs(sortTypes);
        return new PageQuery(request.page(), request.size(), sort);
    }

    private static @NotNull List<Pair<InfluencerSortType, Direction>> getSortTypePairs(String[] sortTypes) {
        List<Pair<InfluencerSortType, Direction>> sort = new ArrayList<>();
        for (String sortType : sortTypes) {
            String[] split = sortType.split(",");
            InfluencerSortType influencerSortType = InfluencerSortType.from(split[0]);
            Direction direction = Direction.from(split[1]);
            sort.add(Pair.of(influencerSortType, direction));
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
