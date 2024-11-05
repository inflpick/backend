package com.leesh.inflpick.v2.influencer.adapter.in.web.controller;

import com.leesh.inflpick.v2.common.application.dto.OffsetPageRequest;
import com.leesh.inflpick.v2.common.application.dto.OffsetPageResponse;
import com.leesh.inflpick.v2.influencer.adapter.out.docs.swagger.GetInfluencerControllerDocs;
import com.leesh.inflpick.v2.influencer.application.dto.InfluencerResponse;
import com.leesh.inflpick.v2.influencer.application.port.in.GetInfluencerUseCase;
import com.leesh.inflpick.v2.influencer.domain.vo.InfluencerId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping(path = "/influencers")
@RestController
public class GetInfluencerController implements GetInfluencerControllerDocs {

    private final GetInfluencerUseCase getInfluencerUseCase;

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InfluencerResponse> get(@PathVariable(value = "id") String id) {
        InfluencerId influencerId = InfluencerId.create(id);
        InfluencerResponse response = getInfluencerUseCase.get(influencerId);
        return ResponseEntity.ok(response);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OffsetPageResponse<InfluencerResponse>> getPage(@RequestParam(name = "page", required = false, defaultValue = "0")                                                                             Integer page,
                                                                          @RequestParam(name = "size", required = false, defaultValue = "20")
                                                                             Integer size,
                                                                          @RequestParam(name = "sort", required = false, defaultValue = "createdDate,asc")
                                                                             String[] sort) {
        OffsetPageRequest request = OffsetPageRequest.create(page, size, sort);
        OffsetPageResponse<InfluencerResponse> influencerPage = getInfluencerUseCase.getPage(request);
        return ResponseEntity.ok().body(influencerPage);
    }

}
