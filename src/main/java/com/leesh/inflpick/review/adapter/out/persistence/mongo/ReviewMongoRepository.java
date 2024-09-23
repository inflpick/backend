package com.leesh.inflpick.review.adapter.out.persistence.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReviewMongoRepository extends MongoRepository<ReviewDocument, String> {

    List<ReviewDocument> findAllByInfluencerId(String id);

}
