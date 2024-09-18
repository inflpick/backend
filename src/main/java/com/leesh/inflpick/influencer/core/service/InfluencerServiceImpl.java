package com.leesh.inflpick.influencer.core.service;

import com.leesh.inflpick.common.port.out.UuidHolder;
import com.leesh.inflpick.influencer.core.domain.Influencer;
import com.leesh.inflpick.influencer.port.in.InfluencerCreateCommand;
import com.leesh.inflpick.influencer.port.in.InfluencerCreateService;
import com.leesh.inflpick.influencer.port.in.InfluencerReadService;
import com.leesh.inflpick.influencer.port.out.InfluencerRepository;
import com.leesh.inflpick.keyword.port.out.KeywordRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Builder
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class InfluencerServiceImpl implements InfluencerReadService, InfluencerCreateService {

    private final UuidHolder uuidHolder;
    private final InfluencerRepository influencerRepository;
    private final KeywordRepository keywordRepository;

    @Override
    public Influencer getByUuid(String uuid) {
        return influencerRepository.getByUuid(uuid);
    }

    @Transactional()
    @Override
    public Influencer create(InfluencerCreateCommand command) {
        List<String> keywordIds = command.keywordIds();
        Influencer influencer = command.toEntity(uuidHolder);
        return influencerRepository.save(influencer);
    }
}
