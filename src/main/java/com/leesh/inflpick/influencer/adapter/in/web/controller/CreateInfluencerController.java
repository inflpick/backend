package com.leesh.inflpick.influencer.adapter.in.web.controller;

import com.leesh.inflpick.influencer.adapter.out.docs.swagger.CreateInfluencerControllerDocs;
import com.leesh.inflpick.influencer.application.dto.InfluencerRequest;
import com.leesh.inflpick.influencer.application.port.in.CreateInfluencerUseCase;
import com.leesh.inflpick.influencer.domain.vo.InfluencerId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RestController
public class CreateInfluencerController implements CreateInfluencerControllerDocs {

    private final CreateInfluencerUseCase createInfluencerUseCase;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(path = "/influencers", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody InfluencerRequest request) {
        InfluencerId id = createInfluencerUseCase.create(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id.id())
                .toUri();
        return ResponseEntity.created(location)
                .contentType(MediaType.APPLICATION_JSON)
                .build();
    }

}
