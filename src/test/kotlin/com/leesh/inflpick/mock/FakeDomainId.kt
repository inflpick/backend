package com.leesh.inflpick.mock

import com.leesh.inflpick.common.domain.DomainId
import java.util.*

class FakeDomainId: DomainId {

    private val id: String = UUID.randomUUID().toString()

    override fun isSaved(): Boolean {
        return id.isNotEmpty()
    }

    override fun toString(): String {
        return id
    }
}