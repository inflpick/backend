package com.leesh.inflpick.v2.keyword.adapter.in.web.controller;

import com.leesh.inflpick.v2.common.application.dto.OffsetPageRequest;
import com.leesh.inflpick.v2.common.application.dto.OffsetPageResponse;
import com.leesh.inflpick.v2.keyword.adapter.out.docs.swagger.GetKeywordControllerDocs;
import com.leesh.inflpick.v2.keyword.application.dto.KeywordResponse;
import com.leesh.inflpick.v2.keyword.application.port.in.GetKeywordUseCase;
import com.leesh.inflpick.v2.keyword.domain.vo.KeywordId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/keywords")
@RestController
public class GetKeywordController implements GetKeywordControllerDocs {

    private final GetKeywordUseCase getKeywordUseCase;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<KeywordResponse> get(@PathVariable(value = "id") String id) {
        KeywordId keywordId = KeywordId.create(id);
        KeywordResponse response = getKeywordUseCase.get(keywordId);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(produces = "application/json")
    public ResponseEntity<OffsetPageResponse<KeywordResponse>> getPage(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                                                       @RequestParam(name = "size", required = false, defaultValue = "20") Integer size,
                                                                       @RequestParam(name = "sort", required = false, defaultValue = "createdDate,asc") String[] sort) {
        OffsetPageRequest request = OffsetPageRequest.create(page, size, sort);
        OffsetPageResponse<KeywordResponse> keywordPage = getKeywordUseCase.getPage(request);
        return ResponseEntity.ok().body(keywordPage);
    }

}
