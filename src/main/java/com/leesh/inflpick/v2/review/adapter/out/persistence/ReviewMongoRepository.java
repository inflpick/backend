package com.leesh.inflpick.v2.review.adapter.out.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReviewMongoRepository extends MongoRepository<ReviewDocument, String> {
}
