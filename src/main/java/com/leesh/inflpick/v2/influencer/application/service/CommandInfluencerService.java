package com.leesh.inflpick.v2.influencer.application.service;

import com.leesh.inflpick.v2.influencer.application.dto.InfluencerRequest;
import com.leesh.inflpick.v2.influencer.application.exception.InfluencerNotFoundException;
import com.leesh.inflpick.v2.influencer.application.port.in.CommandInfluencerUseCase;
import com.leesh.inflpick.v2.influencer.application.port.out.CommandInfluencerPort;
import com.leesh.inflpick.v2.influencer.application.port.out.QueryInfluencerPort;
import com.leesh.inflpick.v2.influencer.domain.Influencer;
import com.leesh.inflpick.v2.influencer.domain.vo.InfluencerId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class CommandInfluencerService implements CommandInfluencerUseCase {

    private final CommandInfluencerPort commandInfluencerPort;
    private final QueryInfluencerPort queryInfluencerPort;

    @Override
    public InfluencerId create(InfluencerRequest request) {
        Influencer influencer = request.toEntity();
        influencer.addKeywords(request.keywordIds());
        influencer.addSnsProfileLinks(request.snsProfileLinks());
        return commandInfluencerPort.save(influencer);
    }

    @Override
    public void update(InfluencerId id, InfluencerRequest influencerRequest) {
        Influencer influencer = queryInfluencerPort.query(id)
                .orElseThrow(() -> new InfluencerNotFoundException("Influencer not found, id: %s".formatted(id)));
        influencer.update(
                influencerRequest.name(),
                influencerRequest.introduction(),
                influencerRequest.description(),
                influencerRequest.keywordIds(),
                influencerRequest.snsProfileLinks());
        commandInfluencerPort.save(influencer);
    }

    @Override
    public void delete(InfluencerId id) {
        commandInfluencerPort.delete(id);
    }
}
