package com.leesh.inflpick.v2.influencer.application.service;

import com.leesh.inflpick.v2.influencer.application.dto.QueryInfluencerResponse;
import com.leesh.inflpick.v2.influencer.application.exception.InfluencerNotFoundException;
import com.leesh.inflpick.v2.influencer.application.port.in.QueryInfluencerUseCase;
import com.leesh.inflpick.v2.influencer.application.port.out.QueryInfluencerPort;
import com.leesh.inflpick.v2.influencer.domain.Influencer;
import com.leesh.inflpick.v2.influencer.domain.vo.InfluencerId;
import com.leesh.inflpick.v2.keyword.application.port.out.QueryKeywordPort;
import com.leesh.inflpick.v2.keyword.domain.vo.Keyword;
import com.leesh.inflpick.v2.shared.application.dto.PageRequest;
import com.leesh.inflpick.v2.shared.application.dto.PageResponse;
import com.leesh.inflpick.v2.shared.application.port.out.storage.StoragePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class QueryInfluencerService implements QueryInfluencerUseCase {

    private final QueryInfluencerPort queryInfluencerPort;
    private final QueryKeywordPort queryKeywordPort;
    private final StoragePort storagePort;

    @Override
    public QueryInfluencerResponse query(InfluencerId influencerId) {
        Influencer influencer = queryInfluencerPort.query(influencerId)
                .orElseThrow(() -> new InfluencerNotFoundException("Influencer not found. influencerId: " + influencerId));
        List<Keyword> keywords = queryKeywordPort.query(influencer.getId());
        String profileImageUrl = storagePort.getUrlString(influencer.getProfileImage().getPath());
        return new QueryInfluencerResponse(influencer, keywords, profileImageUrl);
    }

    @Override
    public PageResponse<QueryInfluencerResponse> query(PageRequest request) {
        PageResponse<Influencer> pageResponse = queryInfluencerPort.query(request);
        List<QueryInfluencerResponse> influencerResponse = pageResponse.contents().stream().map(influencer -> {
            List<Keyword> keywords = queryKeywordPort.query(influencer.getId());
            String profileImageUrl = storagePort.getUrlString(influencer.getProfileImage().getPath());
            return new QueryInfluencerResponse(influencer, keywords, profileImageUrl);
        }).toList();
        return new PageResponse<>(influencerResponse,
                pageResponse.currentPage(),
                pageResponse.totalPages(),
                pageResponse.size(),
                pageResponse.totalElements(),
                pageResponse.sortProperties());
    }
}
