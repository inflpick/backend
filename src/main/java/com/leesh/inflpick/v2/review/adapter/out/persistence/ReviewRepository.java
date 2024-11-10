package com.leesh.inflpick.v2.review.adapter.out.persistence;

import com.leesh.inflpick.v2.common.application.dto.CursorResponse;
import com.leesh.inflpick.v2.review.application.dto.CursorRequest;
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
    public void delete(ReviewId id) {
        reviewMongoRepository.deleteById(id.id());
    }

    @Override
    public Optional<Review> query(ReviewId id) {
        return reviewMongoRepository.findById(id.id())
                .map(ReviewDocument::toEntity);

    }

    @Override
    public CursorResponse<Review> query(CursorRequest request) {

        Criteria criteria = new Criteria();
        criteria.and("reviewDate").gt(request.cursor());

        if (!request.influencerId().isEmpty()) {
            criteria.and("influencerId").is(request.influencerId().id());
        }

        if (!request.productId().isEmpty()) {
            criteria.and("productId").is(request.productId().id());
        }

        Query query = new Query(criteria);
        query.limit(request.limit() + 1);
        query.with(Sort.by(Sort.Order.desc("reviewDate")));

        List<ReviewDocument> documents = mongoTemplate.find(query, ReviewDocument.class);
        List<Review> reviews = documents.stream()
                .map(ReviewDocument::toEntity)
                .toList();

        boolean hasNext = reviews.size() > request.limit();
        if (hasNext) {
            reviews.removeLast();
        }

        return new CursorResponse<>(request.limit(), reviews, hasNext);
    }
}
