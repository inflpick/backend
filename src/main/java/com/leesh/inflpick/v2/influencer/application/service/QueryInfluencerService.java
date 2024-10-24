package com.leesh.inflpick.v2.influencer.application.service;

import com.leesh.inflpick.v2.influencer.application.exception.InfluencerNotFoundException;
import com.leesh.inflpick.v2.influencer.application.port.in.QueryInfluencerUseCase;
import com.leesh.inflpick.v2.influencer.application.port.out.QueryInfluencerPort;
import com.leesh.inflpick.v2.influencer.domain.Influencer;
import com.leesh.inflpick.v2.influencer.domain.vo.InfluencerId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class QueryInfluencerService implements QueryInfluencerUseCase {

    private final QueryInfluencerPort queryInfluencerPort;

    @Override
    public Influencer query(InfluencerId influencerId) {
        return queryInfluencerPort.query(influencerId)
                .orElseThrow(() -> new InfluencerNotFoundException("Influencer not found. influencerId: " + influencerId));
    }
}
