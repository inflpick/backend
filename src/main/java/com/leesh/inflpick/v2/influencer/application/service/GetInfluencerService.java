package com.leesh.inflpick.v2.influencer.application.service;

import com.leesh.inflpick.v2.common.application.dto.OffsetPageRequest;
import com.leesh.inflpick.v2.common.application.dto.OffsetPageResponse;
import com.leesh.inflpick.v2.common.application.port.out.storage.StoragePort;
import com.leesh.inflpick.v2.influencer.application.dto.InfluencerResponse;
import com.leesh.inflpick.v2.influencer.application.exception.InfluencerNotFoundException;
import com.leesh.inflpick.v2.influencer.application.port.in.GetInfluencerUseCase;
import com.leesh.inflpick.v2.influencer.application.port.out.QueryInfluencerPort;
import com.leesh.inflpick.v2.influencer.domain.Influencer;
import com.leesh.inflpick.v2.influencer.domain.vo.InfluencerId;
import com.leesh.inflpick.v2.keyword.application.port.out.QueryKeywordPort;
import com.leesh.inflpick.v2.keyword.domain.Keywords;
import com.leesh.inflpick.v2.keyword.domain.Keyword;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class GetInfluencerService implements GetInfluencerUseCase {

    private final QueryInfluencerPort queryInfluencerPort;
    private final QueryKeywordPort queryKeywordPort;
    private final StoragePort storagePort;

    @Override
    public InfluencerResponse get(InfluencerId id) {
        Influencer influencer = queryInfluencerPort.query(id)
                .orElseThrow(() -> new InfluencerNotFoundException(id));
        Keywords keywords = influencer.keywords();
        List<Keyword> influencerKeywords = queryKeywordPort.query(keywords.ids());
        String profileImageUrl = storagePort.getUrlString(influencer.profileImage().path());
        return InfluencerResponse.from(influencer, influencerKeywords, profileImageUrl);
    }

    @Override
    public OffsetPageResponse<InfluencerResponse> getPage(OffsetPageRequest request) {
        OffsetPageResponse<Influencer> influencerPage = queryInfluencerPort.query(request);
        List<InfluencerResponse> influencerResponse = influencerPage.contents().stream()
                .map(influencer -> {
                    Keywords keywords = influencer.keywords();
                    List<Keyword> influencerKeywords = queryKeywordPort.query(keywords.ids());
                    String profileImageUrl = storagePort.getUrlString(influencer.profileImage().path());
                    return InfluencerResponse.from(influencer, influencerKeywords, profileImageUrl);
                })
                .toList();
        return OffsetPageResponse.create(influencerResponse,
                influencerPage.currentPage(),
                influencerPage.totalPages(),
                influencerPage.size(),
                influencerPage.totalElements(),
                influencerPage.sortProperties());
    }

}
