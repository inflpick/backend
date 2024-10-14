package com.leesh.inflpick.influencer.port;

import com.leesh.inflpick.common.port.SortDirection;
import com.leesh.inflpick.common.port.SortType;
import com.leesh.inflpick.influencer.core.domain.exception.InvalidInfluencerSortTypeException;
import org.springframework.data.util.Pair;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public enum InfluencerSortType implements SortType {

    NAME("name"),
    CREATED_DATE("createdDate"),
    LAST_MODIFIED_DATE("lastModifiedDate"),
    ;

    private final String value;

    InfluencerSortType(String value) {
        this.value = value;
    }

    public static List<String> availableSortTypes() {
        return Arrays.stream(InfluencerSortType.values())
                .map(InfluencerSortType::getSortValue)
                .collect(Collectors.toList());
    }

    private static InfluencerSortType findMatchTypeOrThrows(String sort) {
        InfluencerSortType[] values = InfluencerSortType.values();
        for (InfluencerSortType value : values) {
            if (value.getSortValue().equals(sort)) {
                return value;
            }
        }
        throw new InvalidInfluencerSortTypeException(sort);
    }

    public static Collection<Pair<SortType, SortDirection>> toSortPairs(String[] sortTypes) {
        return Arrays.stream(sortTypes)
                .map(sortType -> {
                    String[] split = sortType.split(",");
                    SortType influencerSortType = InfluencerSortType.findMatchTypeOrThrows(split[0]);
                    SortDirection sortDirection = SortDirection.findMatchTypeOrThrows(split[1]);
                    return Pair.of(influencerSortType, sortDirection);
                })
                .collect(Collectors.toList());
    }

    @Override
    public String getSortValue() {
        return value;
    }
}
