package com.leesh.inflpick.review.adapter.out.persistence;

import com.leesh.inflpick.common.port.CursorPage;
import com.leesh.inflpick.influencer.core.domain.Influencer;
import com.leesh.inflpick.influencer.port.out.InfluencerRepository;
import com.leesh.inflpick.product.core.domain.Product;
import com.leesh.inflpick.product.port.out.ProductRepository;
import com.leesh.inflpick.review.adapter.out.persistence.mongo.ReviewDocument;
import com.leesh.inflpick.review.adapter.out.persistence.mongo.ReviewMongoRepository;
import com.leesh.inflpick.review.core.domain.Review;
import com.leesh.inflpick.review.port.ReviewCursorQuery;
import com.leesh.inflpick.review.port.out.ReviewNotFoundException;
import com.leesh.inflpick.review.port.out.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ReviewRepositoryImpl implements ReviewRepository {

    private final ReviewMongoRepository reviewMongoRepository;
    private final InfluencerRepository influencerRepository;
    private final ProductRepository productRepository;
    private final MongoTemplate mongoTemplate;

    @Override
    public long count() {
        return reviewMongoRepository.count();
    }

    @Override
    public @NotNull Review getById(@NotNull String id) throws ReviewNotFoundException {
        ReviewDocument reviewDocument = reviewMongoRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException(id));
        Influencer reviewer = influencerRepository.getById(reviewDocument.getInfluencerId());
        Product product = productRepository.getById(reviewDocument.getProductId());
        return reviewDocument.toEntity(reviewer, product);
    }

    @Override
    public String save(Review review) {
        ReviewDocument reviewDocument = ReviewDocument.from(review);
        return reviewMongoRepository.save(reviewDocument).getId();
    }

    public CursorPage<Review> getCursorPage(ReviewCursorQuery query) {

        Criteria criteria = new Criteria();
        criteria.and("reviewedDate").gt(query.cursor());

        if (!query.influencerId().isBlank()) {
            criteria.and("influencerId").is(query.influencerId());
        }

        if (!query.productId().isBlank()) {
            criteria.and("productId").is(query.productId());
        }

        Query mongoQuery = new Query(criteria);
        // Fetch one more item to determine if there's a next slice
        mongoQuery.limit(query.limit() + 1);
        mongoQuery.with(Sort.by(Sort.Order.desc("reviewedDate")));

        List<ReviewDocument> documents = mongoTemplate.find(mongoQuery, ReviewDocument.class);
        List<Review> reviews = convertToEntities(documents);

        boolean hasNext = reviews.size() > query.limit();

        if (hasNext) {
            reviews.removeLast();
        }

        return new CursorPage<>(
                query.limit(),
                reviews,
                hasNext);
    }

    private @NotNull List<Review> convertToEntities(List<ReviewDocument> documents) {
        return documents.stream().map(reviewDocument -> {
            Influencer reviewer = influencerRepository.getById(reviewDocument.getInfluencerId());
            Product product = productRepository.getById(reviewDocument.getProductId());
            return reviewDocument.toEntity(reviewer, product);
        }).toList();
    }
}
