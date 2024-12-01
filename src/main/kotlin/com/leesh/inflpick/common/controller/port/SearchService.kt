package com.leesh.inflpick.common.controller.port

import com.leesh.inflpick.common.controller.SearchResponse

interface SearchService {

    fun search(keyword: String): SearchResponse

}
