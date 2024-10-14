package com.leesh.inflpick.influencer.port;

import com.leesh.inflpick.influencer.core.domain.exception.InvalidInfluencerSortTypeException;
import lombok.Getter;

import java.util.Arrays;
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

    public static InfluencerSortType from(String sort) {
        return findOrThrowMatchType(sort);
    }

    private static InfluencerSortType findOrThrowMatchType(String sort) {
        InfluencerSortType[] values = InfluencerSortType.values();
        for (InfluencerSortType value : values) {
            if (value.getValue().equals(sort)) {
                return value;
            }
        }
        throw new InvalidInfluencerSortTypeException(sort);
    }
}
