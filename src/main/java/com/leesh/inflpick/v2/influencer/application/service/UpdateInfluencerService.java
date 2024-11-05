package com.leesh.inflpick.v2.influencer.application.service;

import com.leesh.inflpick.v2.influencer.application.dto.InfluencerRequest;
import com.leesh.inflpick.v2.influencer.application.exception.InfluencerNotFoundException;
import com.leesh.inflpick.v2.influencer.application.port.in.UpdateInfluencerUseCase;
import com.leesh.inflpick.v2.influencer.application.port.out.CommandInfluencerPort;
import com.leesh.inflpick.v2.influencer.application.port.out.QueryInfluencerPort;
import com.leesh.inflpick.v2.influencer.domain.Influencer;
import com.leesh.inflpick.v2.influencer.domain.vo.InfluencerId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class UpdateInfluencerService implements UpdateInfluencerUseCase {

    private final QueryInfluencerPort queryInfluencerPort;
    private final CommandInfluencerPort commandInfluencerPort;

    @Override
    public void update(InfluencerId influencerId, InfluencerRequest command) {
        Influencer influencer = queryInfluencerPort.query(influencerId)
                .orElseThrow(() -> new InfluencerNotFoundException(influencerId));
        Influencer updatedInfluencer = influencer.update(command);
        commandInfluencerPort.save(updatedInfluencer);
    }
}
