package com.leesh.inflpick.influencer.port;

import com.leesh.inflpick.common.core.Direction;
import com.leesh.inflpick.influencer.core.domain.exception.InvalidInfluencerSortTypeException;
import lombok.Getter;
import org.springframework.data.util.Pair;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public enum InfluencerSortType {

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
                .map(InfluencerSortType::getValue)
                .collect(Collectors.toList());
    }

    private static InfluencerSortType findMatchTypeOrThrows(String sort) {
        InfluencerSortType[] values = InfluencerSortType.values();
        for (InfluencerSortType value : values) {
            if (value.getValue().equals(sort)) {
                return value;
            }
        }
        throw new InvalidInfluencerSortTypeException(sort);
    }

    public static Collection<Pair<InfluencerSortType, Direction>> toSortPairs(String[] sortTypes) {
        return Arrays.stream(sortTypes)
                .map(sortType -> {
                    String[] split = sortType.split(",");
                    InfluencerSortType influencerSortType = InfluencerSortType.findMatchTypeOrThrows(split[0]);
                    Direction direction = Direction.findMatchTypeOrThrows(split[1]);
                    return Pair.of(influencerSortType, direction);
                })
                .collect(Collectors.toList());
    }
}
