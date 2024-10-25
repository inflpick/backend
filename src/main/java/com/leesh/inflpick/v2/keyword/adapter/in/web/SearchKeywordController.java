package com.leesh.inflpick.v2.keyword.adapter.in.web;

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

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<KeywordResponse>> search(@RequestParam(value = "name") String name) {
        KeywordName keywordName = KeywordName.create(name);
        List<KeywordResponse> keywordResponses = searchKeywordUseCase.search(keywordName)
                .stream()
                .map(KeywordResponse::from)
                .toList();
        return ResponseEntity.ok(keywordResponses);
    }

}
