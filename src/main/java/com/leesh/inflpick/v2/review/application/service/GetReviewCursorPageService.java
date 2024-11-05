package com.leesh.inflpick.v2.review.application.service;

import com.leesh.inflpick.v2.influencer.application.exception.InfluencerNotFoundException;
import com.leesh.inflpick.v2.influencer.application.port.out.QueryInfluencerPort;
import com.leesh.inflpick.v2.influencer.domain.Influencer;
import com.leesh.inflpick.v2.influencer.domain.vo.InfluencerId;
import com.leesh.inflpick.v2.product.application.exception.ProductNotFoundException;
import com.leesh.inflpick.v2.product.application.port.out.QueryProductPort;
import com.leesh.inflpick.v2.product.domain.Product;
import com.leesh.inflpick.v2.product.domain.vo.ProductId;
import com.leesh.inflpick.v2.review.application.dto.GetCursorPageResponse;
import com.leesh.inflpick.v2.review.application.dto.GetReviewCursorPageRequest;
import com.leesh.inflpick.v2.review.application.dto.ReviewResponse;
import com.leesh.inflpick.v2.review.application.port.in.GetReviewCursorPageUseCase;
import com.leesh.inflpick.v2.review.application.port.out.QueryReviewPort;
import com.leesh.inflpick.v2.review.domain.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class GetReviewCursorPageService implements GetReviewCursorPageUseCase {

    private final QueryReviewPort queryReviewPort;
    private final QueryProductPort queryProductPort;
    private final QueryInfluencerPort queryInfluencerPort;

    @Override
    public GetCursorPageResponse<ReviewResponse> getCursorPage(GetReviewCursorPageRequest request) {

        GetCursorPageResponse<Review> reviewPage = queryReviewPort.query(request.influencerId(),
                request.productId(),
                request.cursor(),
                request.limit());

        List<ReviewResponse> responses = reviewPage.contents().stream().map(review -> {
            ProductId productId = review.productId();
            Product product = queryProductPort.query(productId).orElseThrow(() -> new ProductNotFoundException(productId));
            InfluencerId influencerId = review.influencerId();
            Influencer influencer = queryInfluencerPort.query(influencerId).orElseThrow(() -> new InfluencerNotFoundException(influencerId));
            return ReviewResponse.from(review, influencer, product);
        }).toList();

        return new GetCursorPageResponse<>(reviewPage.limit(), responses, reviewPage.hasNext());
    }
}
