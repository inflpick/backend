package com.leesh.inflpick.common.domain

interface DomainId {
    fun isSaved(): Boolean
    override fun toString(): String
}