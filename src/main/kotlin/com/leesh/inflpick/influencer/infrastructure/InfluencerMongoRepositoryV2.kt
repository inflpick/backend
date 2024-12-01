package com.leesh.inflpick.influencer.infrastructure

import org.springframework.data.mongodb.repository.MongoRepository

interface InfluencerMongoRepositoryV2: MongoRepository<InfluencerDocumentV2, String>  {
}