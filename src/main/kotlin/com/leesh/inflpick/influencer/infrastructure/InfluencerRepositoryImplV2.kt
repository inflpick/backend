package com.leesh.inflpick.influencer.infrastructure

import com.leesh.inflpick.influencer.domin.InfluencerV2
import com.leesh.inflpick.influencer.service.port.InfluencerRepositoryV2
import org.springframework.stereotype.Repository

@Repository
class InfluencerRepositoryImplV2(val influencerMongoRepositoryV2: InfluencerMongoRepositoryV2): InfluencerRepositoryV2 {

    override fun saveAll(influencerV2s: List<InfluencerV2>): List<InfluencerV2> {
        TODO("Not yet implemented")
    }

    override fun save(influencerV2: InfluencerV2): InfluencerV2 {
        TODO("Not yet implemented")
    }

    override fun searchByName(keyword: String): List<InfluencerV2> {
        TODO("Not yet implemented")
    }

}