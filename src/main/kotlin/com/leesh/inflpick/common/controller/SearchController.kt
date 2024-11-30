package com.leesh.inflpick.common.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/search")
@RestController
class SearchController: SearchControllerDocs {

    @GetMapping("")
    override fun search() {
        
    }

}