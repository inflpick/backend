package com.leesh.inflpick.keyword.adapter.in.web.controller;

import com.leesh.inflpick.keyword.adapter.out.docs.swagger.CreateKeywordControllerDocs;
import com.leesh.inflpick.keyword.application.dto.KeywordRequest;
import com.leesh.inflpick.keyword.application.port.in.CreateKeywordUseCase;
import com.leesh.inflpick.keyword.domain.vo.KeywordId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RequestMapping("/keywords")
@RestController
public class CreateKeywordController implements CreateKeywordControllerDocs {

    private final CreateKeywordUseCase createKeywordUseCase;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody KeywordRequest request) {
        KeywordId id = createKeywordUseCase.create(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id.id())
                .toUri();
        return ResponseEntity.created(location)
                .contentType(MediaType.APPLICATION_JSON)
                .build();
    }
}
