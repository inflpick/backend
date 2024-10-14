package com.leesh.inflpick.common.port;

import com.leesh.inflpick.common.port.in.exception.InvalidDirectionException;
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

    public static List<String> availableDirections() {
        return Arrays.stream(SortDirection.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    public static SortDirection findMatchTypeOrThrows(String direction) {
        SortDirection[] values = SortDirection.values();
        for (SortDirection value : values) {
            if (value.getValue().equals(direction)) {
                return value;
            }
        }
        throw new InvalidDirectionException(direction);
    }

    public boolean isAscending() {
        return this == ASC;
    }

    public boolean isDescending() {
        return this == DESC;
    }
}
