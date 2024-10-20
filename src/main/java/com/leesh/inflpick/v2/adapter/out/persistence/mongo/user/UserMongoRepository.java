package com.leesh.inflpick.v2.adapter.out.persistence.mongo.user;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

interface UserMongoRepository extends MongoRepository<UserDocument, String> {

    Optional<UserDocument> findByOauth2IdAndOauth2Provider(String oauth2Id, String oauth2Provider);

    Optional<UserDocument> findByAuthenticationCode(String authenticationCode);

}
