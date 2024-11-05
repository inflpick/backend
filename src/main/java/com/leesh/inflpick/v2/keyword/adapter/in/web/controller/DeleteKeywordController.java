package com.leesh.inflpick.v2.keyword.adapter.in.web.controller;

import com.leesh.inflpick.v2.keyword.adapter.out.docs.swagger.DeleteKeywordControllerDocs;
import com.leesh.inflpick.v2.keyword.application.port.in.DeleteKeywordUseCase;
import com.leesh.inflpick.v2.keyword.domain.vo.KeywordId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/keywords")
@RestController
public class DeleteKeywordController implements DeleteKeywordControllerDocs {

    private final DeleteKeywordUseCase deleteKeywordUseCase;

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") String id) {
        KeywordId keywordId = KeywordId.create(id);
        deleteKeywordUseCase.delete(keywordId);
        return ResponseEntity.noContent().build();
    }
}
