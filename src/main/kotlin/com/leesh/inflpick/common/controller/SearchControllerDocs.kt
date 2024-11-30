package com.leesh.inflpick.common.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag

@Tag(name = "Search", description = "검색 API")
interface SearchControllerDocs {

    @Operation(summary = "검색", description = "검색 API")
    fun search()

}