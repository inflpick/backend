package com.leesh.inflpick.influencer.service.port

import com.leesh.inflpick.influencer.domin.InfluencerV2

interface InfluencerRepositoryV2 {
    fun saveAll(influencerV2s: List<InfluencerV2>): List<InfluencerV2>
    fun save(influencerV2: InfluencerV2): InfluencerV2
    fun searchByName(keyword: String): List<InfluencerV2>
}
