package com.leesh.inflpick.review.adapter.out.persistence;

import com.leesh.inflpick.influencer.core.domain.Influencer;
import com.leesh.inflpick.influencer.port.out.InfluencerRepository;
import com.leesh.inflpick.product.core.Product;
import com.leesh.inflpick.product.port.out.ProductRepository;
import com.leesh.inflpick.review.adapter.out.persistence.mongo.ReviewDocument;
import com.leesh.inflpick.review.adapter.out.persistence.mongo.ReviewMongoRepository;
import com.leesh.inflpick.review.core.domain.Review;
import com.leesh.inflpick.review.port.out.ReviewNotFoundException;
import com.leesh.inflpick.review.port.out.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ReviewRepositoryImpl implements ReviewRepository {

    private final ReviewMongoRepository reviewMongoRepository;
    private final InfluencerRepository influencerRepository;
    private final ProductRepository productRepository;

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

    @Override
    public List<Review> getAllByReviewerId(String id) {
        List<ReviewDocument> reviewDocuments = reviewMongoRepository.findAllByInfluencerId(id);
        return reviewDocuments.stream()
                .map(reviewDocument -> {
                    Influencer reviewer = influencerRepository.getById(reviewDocument.getInfluencerId());
                    Product product = productRepository.getById(reviewDocument.getProductId());
                    return reviewDocument.toEntity(reviewer, product);
                })
                .toList();
    }
}
