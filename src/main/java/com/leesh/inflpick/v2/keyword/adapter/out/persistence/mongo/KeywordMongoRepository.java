package com.leesh.inflpick.v2.keyword.adapter.out.persistence.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface KeywordMongoRepository extends MongoRepository<KeywordDocument, String> {

    void deleteByName(String name);

    @Query("{ 'name' :  { $regex: ?0 } }")
    List<KeywordDocument> searchBy(String value);

    List<KeywordDocument> findByInfluencerId(String influencerId);
}
