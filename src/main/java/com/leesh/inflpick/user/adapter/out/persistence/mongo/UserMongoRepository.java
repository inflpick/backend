package com.leesh.inflpick.user.adapter.out.persistence.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserMongoRepository extends MongoRepository<UserDocument, String> {

    Optional<UserDocument> getByOauth2UserIdAndOauth2Type(String oauth2UserId, String oauth2Type);
}
