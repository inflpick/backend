package com.leesh.inflpick.influencer.adapter.out.persistence.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface InfluencerMongoRepository extends MongoRepository<InfluencerDocument, String> {
    Optional<InfluencerDocument> findByUuid(String uuid);
}
