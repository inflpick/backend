package com.leesh.inflpick.v2.influencer.application.service;

import com.leesh.inflpick.v2.influencer.application.dto.InfluencerRequest;
import com.leesh.inflpick.v2.influencer.application.exception.InfluencerNotFoundException;
import com.leesh.inflpick.v2.influencer.application.port.in.UpdateInfluencerUseCase;
import com.leesh.inflpick.v2.influencer.application.port.out.CommandInfluencerPort;
import com.leesh.inflpick.v2.influencer.application.port.out.QueryInfluencerPort;
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
public class UpdateInfluencerService implements UpdateInfluencerUseCase {

    private final QueryInfluencerPort queryInfluencerPort;
    private final CommandInfluencerPort commandInfluencerPort;
    private final QueryKeywordPort queryKeywordPort;

    @Override
    public void update(InfluencerId influencerId, InfluencerRequest request) {
        Influencer influencer = queryInfluencerPort.query(influencerId)
                .orElseThrow(() -> new InfluencerNotFoundException(influencerId));
        List<Keyword> existsKeywords = findExistsKeywords(request);
        Influencer updatedInfluencer = influencer.update(request);
        Influencer putKeywordInfluencer = updatedInfluencer.putKeywords(existsKeywords);
        commandInfluencerPort.save(putKeywordInfluencer);
    }

    private List<Keyword> findExistsKeywords(InfluencerRequest request) {
        List<KeywordId> keywordIds = request.keywordIds()
                .stream()
                .map(KeywordId::create)
                .toList();
        return queryKeywordPort.query(keywordIds);
    }
}
