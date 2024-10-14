package com.leesh.inflpick.common.port;

import com.leesh.inflpick.common.core.Direction;
import org.springframework.data.util.Pair;

import java.util.Collection;

public record PageQuery(Integer page,
                        Integer size,
                        Collection<Pair<SortType, Direction>> sortPairs) {

    public static PageQuery of(Integer page,
                               Integer size,
                               Collection<Pair<SortType, Direction>> sortPairs) {
        return new PageQuery(page, size, sortPairs);
    }

}
