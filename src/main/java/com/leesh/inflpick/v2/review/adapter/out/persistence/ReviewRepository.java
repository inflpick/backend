package com.leesh.inflpick.v2.review.adapter.out.persistence;

import com.leesh.inflpick.v2.influencer.domain.vo.InfluencerId;
import com.leesh.inflpick.v2.product.domain.vo.ProductId;
import com.leesh.inflpick.v2.review.application.dto.GetCursorPageResponse;
import com.leesh.inflpick.v2.review.application.port.out.CommandReviewPort;
import com.leesh.inflpick.v2.review.application.port.out.QueryReviewPort;
import com.leesh.inflpick.v2.review.domain.Review;
import com.leesh.inflpick.v2.review.domain.vo.ReviewId;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Repository
public class ReviewRepository implements CommandReviewPort, QueryReviewPort {

    private final ReviewMongoRepository reviewMongoRepository;
    private final MongoTemplate mongoTemplate;

    @Override
    public ReviewId save(Review review) {
        ReviewDocument document = ReviewDocument.from(review);
        ReviewDocument savedDocument = reviewMongoRepository.save(document);
        return ReviewId.create(savedDocument.id());
    }

    @Override
    public Optional<Review> query(ReviewId id) {
        return reviewMongoRepository.findById(id.id())
                .map(ReviewDocument::toEntity);

    }

    @Override
    public GetCursorPageResponse<Review> query(InfluencerId influencerId, ProductId productId, Instant cursor, Integer limit) {

        Criteria criteria = new Criteria();
        criteria.and("reviewedDate").gt(cursor);

        if (!influencerId.isEmpty()) {
            criteria.and("influencerId").is(influencerId.id());
        }

        if (!productId.isEmpty()) {
            criteria.and("productId").is(productId.id());
        }

        Query query = new Query(criteria);
        query.limit(limit + 1);
        query.with(Sort.by(Sort.Order.desc("reviewedDate")));

        List<ReviewDocument> documents = mongoTemplate.find(query, ReviewDocument.class);
        List<Review> reviews = documents.stream()
                .map(ReviewDocument::toEntity)
                .toList();

        boolean hasNext = reviews.size() > limit;
        if (hasNext) {
            reviews.removeLast();
        }

        return new GetCursorPageResponse<>(limit, reviews, hasNext);
    }
}
