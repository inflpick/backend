package com.leesh.inflpick.v2.influencer.adapter.in.web;

import com.leesh.inflpick.v2.influencer.application.dto.InfluencerCommand;
import com.leesh.inflpick.v2.influencer.application.port.in.CommandInfluencerUseCase;
import com.leesh.inflpick.v2.influencer.domain.vo.InfluencerId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RequestMapping(path = "/influencers")
@RestController
public class CommandInfluencerController implements CommandInfluencerControllerDocs {

    private final CommandInfluencerUseCase commandInfluencerUseCase;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody InfluencerWebRequest request) {
        InfluencerCommand command = request.toCommand();
        InfluencerId id = commandInfluencerUseCase.create(command);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(location)
                .contentType(MediaType.APPLICATION_JSON)
                .build();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@PathVariable(value = "id") String id,
                                       @RequestBody InfluencerWebRequest request) {
        InfluencerCommand command = request.toCommand();
        InfluencerId influencerId = InfluencerId.create(id);
        commandInfluencerUseCase.update(influencerId, command);
        return ResponseEntity.noContent()
                .header(HttpHeaders.ACCEPT, MediaType.MULTIPART_FORM_DATA_VALUE)
                .build();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable(value = "id") String id) {
        InfluencerId influencerId = InfluencerId.create(id);
        commandInfluencerUseCase.delete(influencerId);
        return ResponseEntity.noContent()
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
