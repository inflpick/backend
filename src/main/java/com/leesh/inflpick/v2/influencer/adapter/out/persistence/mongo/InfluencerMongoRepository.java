package com.leesh.inflpick.v2.influencer.adapter.out.persistence.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface InfluencerMongoRepository extends MongoRepository<InfluencerDocument, String> {
}
