package com.leesh.inflpick.v2.review.application.service;

import com.leesh.inflpick.v2.influencer.application.exception.InfluencerNotFoundException;
import com.leesh.inflpick.v2.influencer.application.port.out.QueryInfluencerPort;
import com.leesh.inflpick.v2.influencer.domain.Influencer;
import com.leesh.inflpick.v2.influencer.domain.vo.InfluencerId;
import com.leesh.inflpick.v2.product.application.exception.ProductNotFoundException;
import com.leesh.inflpick.v2.product.application.port.out.QueryProductPort;
import com.leesh.inflpick.v2.product.domain.Product;
import com.leesh.inflpick.v2.product.domain.vo.ProductId;
import com.leesh.inflpick.v2.review.application.dto.ReviewRequest;
import com.leesh.inflpick.v2.review.application.port.in.CreateReviewUseCase;
import com.leesh.inflpick.v2.review.application.port.out.CommandReviewPort;
import com.leesh.inflpick.v2.review.domain.Review;
import com.leesh.inflpick.v2.review.domain.vo.ReviewId;
import com.leesh.inflpick.v2.review.domain.vo.ReviewSource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class CreateReviewService implements CreateReviewUseCase {

    private final CommandReviewPort commandReviewPort;
    private final QueryInfluencerPort queryInfluencerPort;
    private final QueryProductPort queryProductPort;

    @Override
    public ReviewId create(ReviewRequest request) {

        InfluencerId influencerId = InfluencerId.create(request.influencerId());
        ProductId productId = ProductId.create(request.productId());

        Influencer reviewer = queryInfluencerPort.query(influencerId)
                .orElseThrow(() -> new InfluencerNotFoundException(influencerId));
        Product product = queryProductPort.query(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));

        ReviewSource source = ReviewSource.create(request.contents(), request.url(), request.reviewDate());
        Review review = reviewer.review(product.id(), source);

        return commandReviewPort.save(review);
    }
}
