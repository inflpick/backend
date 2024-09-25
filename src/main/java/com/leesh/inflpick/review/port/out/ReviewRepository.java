package com.leesh.inflpick.review.port.out;

import com.leesh.inflpick.common.adapter.in.web.value.PageRequest;
import com.leesh.inflpick.review.core.domain.Review;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface ReviewRepository {

    long count();

    @NotNull Review getById(@NotNull String id) throws ReviewNotFoundException;

    String save(Review review);

    List<Review> getAllByReviewerId(String id);

    Slice<Review> findAllByReviewerId(String id, PageRequest pageRequest);
}
