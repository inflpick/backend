package com.leesh.inflpick.v2.keyword.adapter.in.web.controller;

import com.leesh.inflpick.v2.keyword.adapter.out.docs.swagger.UpdateKeywordControllerDocs;
import com.leesh.inflpick.v2.keyword.application.dto.KeywordRequest;
import com.leesh.inflpick.v2.keyword.application.port.in.UpdateKeywordUseCase;
import com.leesh.inflpick.v2.keyword.domain.vo.KeywordId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/keywords")
@RestController
public class UpdateKeywordController implements UpdateKeywordControllerDocs {

    private final UpdateKeywordUseCase updateKeywordUseCase;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable(value = "id") String id,
                                       @RequestBody KeywordRequest request) {
        KeywordId keywordId = KeywordId.create(id);
        updateKeywordUseCase.update(keywordId, request);
        return ResponseEntity.noContent().build();
    }
}
