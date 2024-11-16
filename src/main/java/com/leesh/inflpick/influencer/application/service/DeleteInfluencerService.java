package com.leesh.inflpick.influencer.application.service;

import com.leesh.inflpick.influencer.application.port.in.DeleteInfluencerUseCase;
import com.leesh.inflpick.influencer.application.port.out.CommandInfluencerPort;
import com.leesh.inflpick.influencer.domain.vo.InfluencerId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class DeleteInfluencerService implements DeleteInfluencerUseCase {

    private final CommandInfluencerPort commandInfluencerPort;

    @Override
    public void delete(InfluencerId id) {
        commandInfluencerPort.delete(id);
    }
}
