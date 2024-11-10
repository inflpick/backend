package com.leesh.inflpick.v2.influencer.application.service;

import com.leesh.inflpick.v2.influencer.application.dto.InfluencerRequest;
import com.leesh.inflpick.v2.influencer.application.port.in.CreateInfluencerUseCase;
import com.leesh.inflpick.v2.influencer.application.port.out.CommandInfluencerPort;
import com.leesh.inflpick.v2.influencer.domain.Influencer;
import com.leesh.inflpick.v2.influencer.domain.vo.InfluencerId;
import com.leesh.inflpick.v2.keyword.application.port.out.QueryKeywordPort;
import com.leesh.inflpick.v2.keyword.domain.Keyword;
import com.leesh.inflpick.v2.keyword.domain.vo.KeywordId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class CreateInfluencerService implements CreateInfluencerUseCase {

    private final CommandInfluencerPort commandInfluencerPort;
    private final QueryKeywordPort queryKeywordPort;

    @Override
    public InfluencerId create(InfluencerRequest request) {
        List<KeywordId> keywordIds = request.keywordIds().stream()
                .map(KeywordId::create)
                .toList();
        List<Keyword> existsKeywords = queryKeywordPort.query(keywordIds);
        Influencer influencer = request.toEntity();
        Influencer addKeywordInfluencer = influencer.putKeywords(existsKeywords);
        return commandInfluencerPort.save(addKeywordInfluencer);
    }
}
