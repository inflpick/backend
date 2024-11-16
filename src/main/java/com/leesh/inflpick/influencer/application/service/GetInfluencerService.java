package com.leesh.inflpick.influencer.application.service;

import com.leesh.inflpick.common.application.dto.PageRequest;
import com.leesh.inflpick.common.application.dto.PageResponse;
import com.leesh.inflpick.common.application.port.out.storage.StoragePort;
import com.leesh.inflpick.influencer.application.dto.InfluencerResponse;
import com.leesh.inflpick.influencer.application.exception.InfluencerNotFoundException;
import com.leesh.inflpick.influencer.application.port.in.GetInfluencerUseCase;
import com.leesh.inflpick.influencer.application.port.out.QueryInfluencerPort;
import com.leesh.inflpick.influencer.domain.Influencer;
import com.leesh.inflpick.influencer.domain.vo.InfluencerId;
import com.leesh.inflpick.keyword.application.port.out.QueryKeywordPort;
import com.leesh.inflpick.keyword.domain.Keyword;
import com.leesh.inflpick.keyword.domain.Keywords;
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
        return InfluencerResponse.create(influencer, influencerKeywords, profileImageUrl);
    }

    @Override
    public PageResponse<InfluencerResponse> getPage(PageRequest request) {
        PageResponse<Influencer> influencerPage = queryInfluencerPort.query(request);
        List<InfluencerResponse> influencerResponse = influencerPage.contents().stream()
                .map(influencer -> this.get(influencer.id()))
                .toList();
        return PageResponse.create(influencerResponse,
                influencerPage.currentPage(),
                influencerPage.totalPages(),
                influencerPage.size(),
                influencerPage.totalElements(),
                influencerPage.sortProperties());
    }

}
