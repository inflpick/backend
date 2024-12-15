package com.leesh.inflpick.common.domain

class UnsavedId(private val id: String = "") : DomainId {
    override fun isSaved(): Boolean {
        return id.isEmpty()
    }

    override fun toString(): String {
        return id
    }
}