package com.leesh.inflpick.keyword.adapter.out.persistence.mongo;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface KeywordMongoRepository extends MongoRepository<KeywordDocument, String> {

    Optional<KeywordDocument> findByName(String name);

    Optional<KeywordDocument> findByUuid(String uuid);

    @Query("{ 'name' :  { $regex: ?0 } }")
    List<KeywordDocument> searchBy(@NotNull String name);
}
