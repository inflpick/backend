package com.leesh.inflpick.mock

import com.leesh.inflpick.influencer.domin.InfluencerV2
import com.leesh.inflpick.influencer.service.port.InfluencerRepositoryV2
import java.util.concurrent.CopyOnWriteArrayList

class FakeInfluencerRepository(data: List<InfluencerV2>) : InfluencerRepositoryV2 {

    private val data = CopyOnWriteArrayList<InfluencerV2>()

    init {
        this.saveAll(data)
    }

    override fun saveAll(influencerV2s: List<InfluencerV2>): List<InfluencerV2> {
        return influencerV2s.map { save(it) }
    }

    override fun save(influencerV2: InfluencerV2): InfluencerV2 {
        data.removeIf { it == influencerV2 }
        val updatedInfluencer = influencerV2.copy(id = FakeDomainId())
        data.add(updatedInfluencer)
        return influencerV2
    }

    override fun searchByName(keyword: String): List<InfluencerV2> {
        return data.filter { it.name.contains(keyword) }
    }

}