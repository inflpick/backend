package com.leesh.inflpick.common.port;

import com.leesh.inflpick.common.core.Direction;
import org.springframework.data.util.Pair;

import java.util.Collection;

public record PageQuery<T>(Integer page,
                           Integer size,
                           Collection<Pair<T, Direction>> sortPairs) {

    public static <T> PageQuery<T> of(Integer page,
                                      Integer size,
                                      Collection<Pair<T, Direction>> sortPairs) {
        return new PageQuery<>(page, size, sortPairs);
    }

}
