package com.leesh.inflpick.review.core;

import com.leesh.inflpick.common.port.CursorPage;
import com.leesh.inflpick.common.port.out.UuidHolder;
import com.leesh.inflpick.influencer.core.domain.Influencer;
import com.leesh.inflpick.product.core.domain.Product;
import com.leesh.inflpick.review.core.domain.Review;
import com.leesh.inflpick.review.port.ReviewCommand;
import com.leesh.inflpick.review.port.ReviewCursorQuery;
import com.leesh.inflpick.review.port.in.ReviewCommandService;
import com.leesh.inflpick.review.port.in.ReviewQueryService;
import com.leesh.inflpick.review.port.out.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class ReviewServiceImpl implements ReviewCommandService, ReviewQueryService {

    private final UuidHolder uuidHolder;
    private final ReviewRepository reviewRepository;

    @Override
    public String create(Influencer reviewer, Product product, ReviewCommand command) {
        Review review = command.toEntity(uuidHolder, reviewer, product);
        return reviewRepository.save(review);
    }

    @Override
    public CursorPage<Review> getCursorPage(ReviewCursorQuery query) {
        return reviewRepository.getCursorPage(query);
    }

}
