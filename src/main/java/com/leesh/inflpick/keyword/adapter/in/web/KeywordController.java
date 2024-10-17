package com.leesh.inflpick.keyword.adapter.in.web;

import com.leesh.inflpick.keyword.adapter.in.web.docs.KeywordApiDocs;
import com.leesh.inflpick.keyword.adapter.in.web.value.KeywordRequest;
import com.leesh.inflpick.keyword.adapter.in.web.value.KeywordResponse;
import com.leesh.inflpick.keyword.core.domain.KeywordName;
import com.leesh.inflpick.keyword.port.in.KeywordCommandService;
import com.leesh.inflpick.keyword.port.in.KeywordReadService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Builder
@RequiredArgsConstructor
@RequestMapping(path = "/keywords")
@RestController
public class KeywordController implements KeywordApiDocs {

    private final KeywordCommandService commandService;
    private final KeywordReadService readService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody KeywordRequest request) {
        String id = commandService.create(request.toCommand());
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

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<KeywordResponse>> search(@RequestParam(value = "name") String name) {

        KeywordName keywordName = KeywordName.from(name);
        List<KeywordResponse> bodies = readService.search(keywordName)
                .stream()
                .map(KeywordResponse::from)
                .toList();
        return ResponseEntity.ok(bodies);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping(path = "/{name}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@PathVariable String name,
                                       @RequestBody KeywordRequest request) {
        KeywordName keywordName = KeywordName.from(name);
        commandService.update(request.toCommand(), keywordName);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping(path = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable String name) {
        KeywordName keywordName = KeywordName.from(name);
        commandService.delete(keywordName);
        return ResponseEntity.noContent().build();
    }

}
