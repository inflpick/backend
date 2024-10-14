package com.leesh.inflpick.common.core;

import com.leesh.inflpick.common.port.in.exception.InvalidDirectionException;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public enum Direction {

    ASC("asc"),
    DESC("desc"),
    ;

    private final String value;

    Direction(String value) {
        this.value = value;
    }

    public static List<String> availableDirections() {
        return Arrays.stream(Direction.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    public static Direction findMatchTypeOrThrows(String direction) {
        Direction[] values = Direction.values();
        for (Direction value : values) {
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
