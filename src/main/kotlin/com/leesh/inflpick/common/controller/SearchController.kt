package com.leesh.inflpick.common.controller

import com.leesh.inflpick.common.controller.port.SearchService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/search")
@RestController
class SearchController(val searchService: SearchService): SearchControllerDocs {

    @GetMapping
    override fun search(@RequestParam keyword: String): ResponseEntity<SearchResponse> {
        return ResponseEntity.ok(null)
    }

}