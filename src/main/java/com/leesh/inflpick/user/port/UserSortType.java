package com.leesh.inflpick.user.port;

import com.leesh.inflpick.common.core.Direction;
import com.leesh.inflpick.common.port.SortType;
import org.springframework.data.util.Pair;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public enum UserSortType implements SortType {

    NAME("name"),
    CREATED_DATE("createdDate"),
    LAST_MODIFIED_DATE("lastModifiedDate"),
    ;

    private final String value;

    UserSortType(String value) {
        this.value = value;
    }

    public static List<String> availableSortTypes() {
        return Arrays.stream(UserSortType.values())
                .map(UserSortType::getSortValue)
                .collect(Collectors.toList());
    }

    private static UserSortType findMatchTypeOrThrows(String sort) {
        UserSortType[] values = UserSortType.values();
        for (UserSortType value : values) {
            if (value.getSortValue().equals(sort)) {
                return value;
            }
        }
        throw new InvalidUserSortTypeException(sort);
    }

    public static Collection<Pair<UserSortType, Direction>> toSortPairs(String[] sortTypes) {
        return Arrays.stream(sortTypes)
                .map(sortType -> {
                    String[] split = sortType.split(",");
                    UserSortType userSortType = UserSortType.findMatchTypeOrThrows(split[0]);
                    Direction direction = Direction.findMatchTypeOrThrows(split[1]);
                    return Pair.of(userSortType, direction);
                })
                .collect(Collectors.toList());
    }

    @Override
    public String getSortValue() {
        return value;
    }
}
