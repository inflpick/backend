package com.leesh.inflpick.common.port;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public enum SortDirection {

    ASC("asc"),
    DESC("desc"),
    ;

    private final String value;

    SortDirection(String value) {
        this.value = value;
    }

    public static List<String> availableDirectionsValues() {
        return Arrays.stream(SortDirection.values())
                .map(SortDirection::getValue)
                .collect(Collectors.toList());
    }

    public boolean isAscending() {
        return this == ASC;
    }

    public boolean isDescending() {
        return this == DESC;
    }
}
