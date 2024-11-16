package com.leesh.inflpick.keyword.adapter.in.web.controller;

import com.leesh.inflpick.keyword.adapter.out.docs.swagger.DeleteKeywordControllerDocs;
import com.leesh.inflpick.keyword.application.port.in.DeleteKeywordUseCase;
import com.leesh.inflpick.keyword.domain.vo.KeywordId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/keywords")
@RestController
public class DeleteKeywordController implements DeleteKeywordControllerDocs {

    private final DeleteKeywordUseCase deleteKeywordUseCase;

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") String id) {
        KeywordId keywordId = KeywordId.create(id);
        deleteKeywordUseCase.delete(keywordId);
        return ResponseEntity.noContent().build();
    }
}
