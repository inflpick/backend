package com.leesh.inflpick.influencer.adapter.in.web.controller;

import com.leesh.inflpick.influencer.adapter.out.docs.swagger.DeleteInfluencerControllerDocs;
import com.leesh.inflpick.influencer.application.port.in.DeleteInfluencerUseCase;
import com.leesh.inflpick.influencer.domain.vo.InfluencerId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class DeleteInfluencerController implements DeleteInfluencerControllerDocs {

    private final DeleteInfluencerUseCase deleteInfluencerUseCase;

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping(path = "/influencers/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") String id) {
        InfluencerId influencerId = InfluencerId.create(id);
        deleteInfluencerUseCase.delete(influencerId);
        return ResponseEntity.noContent().build();
    }

}
