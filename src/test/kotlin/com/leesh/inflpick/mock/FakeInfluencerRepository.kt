package com.leesh.inflpick.mock

import com.leesh.inflpick.influencer.domin.InfluencerV2
import com.leesh.inflpick.influencer.infrastructure.InfluencerDocumentV2
import com.leesh.inflpick.influencer.service.port.InfluencerRepositoryV2
import java.util.*
import java.util.concurrent.CopyOnWriteArrayList

class FakeInfluencerRepository: InfluencerRepositoryV2 {

    private val data = CopyOnWriteArrayList<InfluencerDocumentV2>()

    constructor()

    constructor(data: List<InfluencerV2>) {
        this.saveAll(data)
    }

    override fun saveAll(influencerV2s: List<InfluencerV2>): List<InfluencerV2> {
        return influencerV2s.map { save(it) }
    }

    override fun save(influencerV2: InfluencerV2): InfluencerV2 {
        val influencerDocumentV2 = when {
            influencerV2.isPersistent() -> data.find { it.id == influencerV2.id }
                ?.also { data.remove(it) }
                ?.let { InfluencerDocumentV2.fromDomain(notPersistentDomain = influencerV2) }
            else -> InfluencerDocumentV2.fromDomain(UUID.randomUUID().toString(), influencerV2)
        }
        influencerDocumentV2?.let { data.add(it) }
        return influencerDocumentV2?.toDomain() ?: influencerV2
    }

    override fun searchByName(keyword: String): List<InfluencerV2> {
        return data.filter { it.name.contains(keyword) }
            .map { it.toDomain() }
    }

}