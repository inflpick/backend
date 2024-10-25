package com.leesh.inflpick.v2.influencer.adapter.in.web.controller;

import com.leesh.inflpick.common.adapter.in.web.WebOffsetPageRequest;
import com.leesh.inflpick.v2.influencer.adapter.in.web.docs.QueryInfluencerControllerDocs;
import com.leesh.inflpick.v2.influencer.adapter.in.web.dto.QueryInfluencerWebResponse;
import com.leesh.inflpick.v2.influencer.application.dto.QueryInfluencerResponse;
import com.leesh.inflpick.v2.influencer.application.port.in.QueryInfluencerUseCase;
import com.leesh.inflpick.v2.influencer.domain.vo.InfluencerId;
import com.leesh.inflpick.v2.shared.adapter.in.web.PageWebResponse;
import com.leesh.inflpick.v2.shared.application.dto.PageRequest;
import com.leesh.inflpick.v2.shared.application.dto.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/influencers")
@RestController
public class QueryInfluencerController implements QueryInfluencerControllerDocs {

    private final QueryInfluencerUseCase queryInfluencerUseCase;

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<QueryInfluencerWebResponse> get(@PathVariable String id) {
        InfluencerId influencerId = InfluencerId.create(id);
        QueryInfluencerResponse response = queryInfluencerUseCase.query(influencerId);
        QueryInfluencerWebResponse webResponse = QueryInfluencerWebResponse.from(response);
        return ResponseEntity.ok(webResponse);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PageWebResponse<QueryInfluencerWebResponse>> list(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                                                            @RequestParam(name = "size", required = false, defaultValue = "20") Integer size,
                                                                            @RequestParam(name = "sort", required = false, defaultValue = "createdDate,asc") String[] sort) {

        PageRequest request = new WebOffsetPageRequest(page, size, sort);
        PageResponse<QueryInfluencerResponse> pageResponse = queryInfluencerUseCase.query(request);
        QueryInfluencerWebResponse[] webResponses = pageResponse.contents()
                .stream()
                .map(QueryInfluencerWebResponse::from)
                .toList()
                .toArray(new QueryInfluencerWebResponse[0]);
        PageWebResponse<QueryInfluencerWebResponse> pageWebResponse = PageWebResponse.of(webResponses, pageResponse);
        return ResponseEntity.ok(pageWebResponse);
    }
}
