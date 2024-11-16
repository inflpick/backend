package com.leesh.inflpick.review.application.service;

import com.leesh.inflpick.common.application.dto.CursorResponse;
import com.leesh.inflpick.common.application.port.out.storage.StoragePort;
import com.leesh.inflpick.influencer.application.dto.InfluencerResponse;
import com.leesh.inflpick.influencer.application.exception.InfluencerNotFoundException;
import com.leesh.inflpick.influencer.application.port.out.QueryInfluencerPort;
import com.leesh.inflpick.influencer.domain.Influencer;
import com.leesh.inflpick.keyword.application.port.out.QueryKeywordPort;
import com.leesh.inflpick.keyword.domain.Keyword;
import com.leesh.inflpick.product.application.dto.ProductResponse;
import com.leesh.inflpick.product.application.exception.ProductNotFoundException;
import com.leesh.inflpick.product.application.port.out.QueryProductPort;
import com.leesh.inflpick.product.domain.Product;
import com.leesh.inflpick.review.application.dto.CursorRequest;
import com.leesh.inflpick.review.application.dto.ReviewResponse;
import com.leesh.inflpick.review.application.port.in.GetReviewUseCase;
import com.leesh.inflpick.review.application.port.out.QueryReviewPort;
import com.leesh.inflpick.review.domain.Review;
import com.leesh.inflpick.review.domain.vo.ReviewId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class GetReviewService implements GetReviewUseCase {

    private final QueryReviewPort queryReviewPort;
    private final QueryProductPort queryProductPort;
    private final QueryInfluencerPort queryInfluencerPort;
    private final QueryKeywordPort queryKeywordPort;
    private final StoragePort storagePort;

    @Override
    public CursorResponse<ReviewResponse> getCursorPage(CursorRequest request) {

        CursorResponse<Review> reviewPage = queryReviewPort.query(request);

        List<ReviewResponse> responses = reviewPage.contents().stream()
                .map(review -> get(review.id()))
                .toList();

        return new CursorResponse<>(reviewPage.limit(), responses, reviewPage.hasNext());
    }

    @Override
    public ReviewResponse get(ReviewId reviewId) {
        Review review = queryReviewPort.query(reviewId).orElseThrow(() -> new RuntimeException("리뷰를 찾을 수 없습니다."));
        InfluencerResponse influencerResponse = getInfluencerResponse(review);
        ProductResponse productResponse = getProductResponse(review);
        return ReviewResponse.create(review, influencerResponse, productResponse);
    }

    private ProductResponse getProductResponse(Review review) {
        Product product = queryProductPort.query(review.productId()).orElseThrow(() -> new ProductNotFoundException(review.productId()));
        List<Keyword> productKeywords = queryKeywordPort.query(product.keywords().ids());
        String productImageUrl = storagePort.getUrlString(product.image().path());
        return ProductResponse.create(product, productKeywords, productImageUrl);
    }

    private InfluencerResponse getInfluencerResponse(Review review) {
        Influencer influencer = queryInfluencerPort.query(review.influencerId()).orElseThrow(() -> new InfluencerNotFoundException(review.influencerId()));
        List<Keyword> influencerKeywords = queryKeywordPort.query(influencer.keywords().ids());
        String profileImageUrl = storagePort.getUrlString(influencer.profileImage().path());
        return InfluencerResponse.create(influencer, influencerKeywords, profileImageUrl);
    }
}
