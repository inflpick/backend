package com.leesh.inflpick.common.controller.port

import com.leesh.inflpick.common.controller.dto.SearchResponse

interface SearchService {

    fun search(keyword: String): SearchResponse

}
