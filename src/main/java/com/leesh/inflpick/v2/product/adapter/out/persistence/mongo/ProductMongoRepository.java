package com.leesh.inflpick.v2.product.adapter.out.persistence.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductMongoRepository extends MongoRepository<ProductDocument, String> {

}
