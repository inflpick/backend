package com.leesh.inflpick.review.adapter.out.persistence.mongo;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReviewMongoRepository extends MongoRepository<ReviewDocument, String> {

    List<ReviewDocument> findAllByInfluencerIdOrderByReviewedDateDesc(String id);

    Slice<ReviewDocument> findAllByInfluencerIdOrderByReviewedDateDesc(String id, Pageable pageable);

}
