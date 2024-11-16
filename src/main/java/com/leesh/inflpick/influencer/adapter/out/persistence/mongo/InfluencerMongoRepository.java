package com.leesh.inflpick.influencer.adapter.out.persistence.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface InfluencerMongoRepository extends MongoRepository<InfluencerDocument, String> {
}
