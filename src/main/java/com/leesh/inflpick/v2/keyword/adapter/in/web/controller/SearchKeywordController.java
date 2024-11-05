package com.leesh.inflpick.v2.keyword.adapter.in.web.controller;

import com.leesh.inflpick.v2.keyword.adapter.out.docs.swagger.SearchKeywordControllerDocs;
import com.leesh.inflpick.v2.keyword.application.dto.KeywordResponse;
import com.leesh.inflpick.v2.keyword.application.port.in.SearchKeywordUseCase;
import com.leesh.inflpick.v2.keyword.domain.vo.KeywordName;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping(path = "/keywords")
@RestController
public class SearchKeywordController implements SearchKeywordControllerDocs {

    private final SearchKeywordUseCase searchKeywordUseCase;

    @GetMapping(path = "/name", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<KeywordResponse>> search(@RequestParam(value = "search") String search) {
        KeywordName keywordName = KeywordName.create(search);
        List<KeywordResponse> responses = searchKeywordUseCase.search(keywordName);
        return ResponseEntity.ok(responses);
    }

}
