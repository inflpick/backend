package com.leesh.inflpick.review.port.out;

import com.leesh.inflpick.common.port.CursorPage;
import com.leesh.inflpick.review.core.domain.Review;
import com.leesh.inflpick.review.port.ReviewCursorQuery;
import org.jetbrains.annotations.NotNull;

public interface ReviewRepository {

    long count();

    @NotNull Review getById(@NotNull String id) throws ReviewNotFoundException;

    String save(Review review);

    CursorPage<Review> getCursorPage(ReviewCursorQuery query);
}
