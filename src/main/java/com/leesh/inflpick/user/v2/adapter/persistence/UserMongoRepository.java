package com.leesh.inflpick.user.v2.adapter.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

interface UserMongoRepository extends MongoRepository<UserDocument, String> {

    Optional<UserDocument> findByOauth2IdAndOauth2Provider(String oauth2Id, String oauth2Provider);
}
