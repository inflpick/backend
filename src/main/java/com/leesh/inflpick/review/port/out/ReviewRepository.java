package com.leesh.inflpick.review.port.out;

import com.leesh.inflpick.review.core.domain.Review;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface ReviewRepository {

    long count();

    @NotNull Review getById(@NotNull String id) throws ReviewNotFoundException;

    String save(Review review);

    List<Review> getAllByReviewerId(String id);
}
