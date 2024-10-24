package com.leesh.inflpick.v2.influencer.adapter.in.web;

import com.leesh.inflpick.common.adapter.in.web.value.WebPageResponse;
import com.leesh.inflpick.v2.influencer.application.port.in.QueryInfluencerUseCase;
import com.leesh.inflpick.v2.influencer.domain.Influencer;
import com.leesh.inflpick.v2.influencer.domain.vo.InfluencerId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/influencers")
@RestController
public class QueryInfluencerController implements QueryInfluencerControllerDocs {

    private final QueryInfluencerUseCase queryInfluencerUseCase;
    private final

    @Override
    public ResponseEntity<InfluencerWebResponse> get(String id) {
        InfluencerId influencerId = InfluencerId.create(id);
        Influencer influencer = queryInfluencerUseCase.query(influencerId);
        return null;
    }

    @Override
    public ResponseEntity<WebPageResponse<InfluencerWebResponse>> list(Integer page, Integer size, String[] sort) {
        return null;
    }
}
