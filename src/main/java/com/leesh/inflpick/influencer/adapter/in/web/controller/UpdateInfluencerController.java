package com.leesh.inflpick.influencer.adapter.in.web.controller;

import com.leesh.inflpick.influencer.adapter.out.docs.swagger.UpdateInfluencerControllerDocs;
import com.leesh.inflpick.influencer.application.dto.InfluencerRequest;
import com.leesh.inflpick.influencer.application.port.in.UpdateInfluencerUseCase;
import com.leesh.inflpick.influencer.domain.vo.InfluencerId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UpdateInfluencerController implements UpdateInfluencerControllerDocs {

    private final UpdateInfluencerUseCase updateInfluencerUseCase;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/influencers/{id}")
    public ResponseEntity<Void> update(@PathVariable(value = "id") String id,
                                       @RequestBody InfluencerRequest request) {
        InfluencerId influencerId = InfluencerId.create(id);
        updateInfluencerUseCase.update(influencerId, request);
        return ResponseEntity.noContent().build();
    }

}
