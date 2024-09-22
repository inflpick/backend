package com.leesh.inflpick.product.adapter.out.persistence.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProductMongoRepository extends MongoRepository<ProductDocument, String> {
    Optional<ProductDocument> findByUuid(String uuid);
}
