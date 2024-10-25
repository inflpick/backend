package com.leesh.inflpick.v2.keyword.adapter.in.web;

import com.leesh.inflpick.v2.keyword.application.dto.KeywordCommand;
import com.leesh.inflpick.v2.keyword.application.port.in.CommandKeywordUseCase;
import com.leesh.inflpick.v2.keyword.domain.vo.KeywordId;
import com.leesh.inflpick.v2.keyword.domain.vo.KeywordName;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RequestMapping(path = "/keywords")
@RestController
public class CommandKeywordController implements CommandKeywordControllerDocs {

    private final CommandKeywordUseCase commandKeywordUseCase;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody KeywordRequest request) {
        KeywordId id = commandKeywordUseCase.create(request.toCommand());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity
                .created(location)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Accept", MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping(path = "/{name}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@PathVariable String name,
                                       @RequestBody KeywordRequest request) {
        KeywordName keywordName = KeywordName.create(name);
        KeywordCommand command = request.toCommand();
        commandKeywordUseCase.update(keywordName, command);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping(path = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable String name) {
        KeywordName keywordName = KeywordName.create(name);
        commandKeywordUseCase.delete(keywordName);
        return ResponseEntity.noContent().build();
    }

}
