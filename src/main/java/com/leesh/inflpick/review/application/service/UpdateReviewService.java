package com.leesh.inflpick.review.application.service;

import com.leesh.inflpick.influencer.domain.vo.InfluencerId;
import com.leesh.inflpick.product.domain.vo.ProductId;
import com.leesh.inflpick.review.application.dto.ReviewRequest;
import com.leesh.inflpick.review.application.exception.ReviewNotFoundException;
import com.leesh.inflpick.review.application.port.in.UpdateReviewUseCase;
import com.leesh.inflpick.review.application.port.out.CommandReviewPort;
import com.leesh.inflpick.review.application.port.out.QueryReviewPort;
import com.leesh.inflpick.review.domain.Review;
import com.leesh.inflpick.review.domain.vo.ReviewId;
import com.leesh.inflpick.review.domain.vo.ReviewSource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class UpdateReviewService implements UpdateReviewUseCase {

    private final CommandReviewPort commandReviewPort;
    private final QueryReviewPort queryReviewPort;

    @Override
    public void update(ReviewId id, ReviewRequest request) {
        Review review = queryReviewPort.query(id)
                .orElseThrow(() -> new ReviewNotFoundException(id));
        InfluencerId influencerId = InfluencerId.create(request.influencerId());
        ProductId productId = ProductId.create(request.productId());
        ReviewSource source = ReviewSource.create(request.contents(), request.url(), request.reviewDate());
        Review updatedReview = review.update(influencerId, productId, source);
        commandReviewPort.save(updatedReview);
    }
}
