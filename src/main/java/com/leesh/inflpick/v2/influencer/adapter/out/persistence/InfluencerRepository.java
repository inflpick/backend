package com.leesh.inflpick.v2.influencer.adapter.out.persistence;

import com.leesh.inflpick.v2.common.adapter.out.persistence.SpringDataPageRequestConverter;
import com.leesh.inflpick.v2.common.application.dto.OffsetPageRequest;
import com.leesh.inflpick.v2.common.application.dto.OffsetPageResponse;
import com.leesh.inflpick.v2.common.application.dto.Sortable;
import com.leesh.inflpick.v2.influencer.adapter.out.persistence.mongo.InfluencerDocument;
import com.leesh.inflpick.v2.influencer.adapter.out.persistence.mongo.InfluencerMongoRepository;
import com.leesh.inflpick.v2.influencer.application.port.out.CommandInfluencerPort;
import com.leesh.inflpick.v2.influencer.application.port.out.QueryInfluencerPort;
import com.leesh.inflpick.v2.influencer.domain.Influencer;
import com.leesh.inflpick.v2.influencer.domain.vo.InfluencerId;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class InfluencerRepository implements CommandInfluencerPort, QueryInfluencerPort {

    private final InfluencerMongoRepository influencerMongoRepository;

    @Override
    public InfluencerId save(Influencer influencer) {
        InfluencerDocument document = InfluencerDocument.from(influencer);
        InfluencerDocument saved = influencerMongoRepository.save(document);
        return InfluencerId.create(saved.id());
    }

    @Override
    public void delete(InfluencerId id) {
        influencerMongoRepository.deleteById(id.id());
    }

    @Override
    public Optional<Influencer> query(InfluencerId id) {
        return influencerMongoRepository.findById(id.id())
                .map(InfluencerDocument::toEntity);
    }

    @Override
    public OffsetPageResponse<Influencer> query(OffsetPageRequest request) {
        Sortable sortable = () -> Arrays.stream(InfluencerSortable.values())
                .map(InfluencerSortable::name)
                .toList();
        PageRequest pageRequest = SpringDataPageRequestConverter.convert(request, sortable);
        Page<InfluencerDocument> documentPage = influencerMongoRepository.findAll(pageRequest);
        List<Influencer> influencers = documentPage
                .map(InfluencerDocument::toEntity)
                .stream()
                .toList();
        return OffsetPageResponse.create(influencers,
                documentPage.getNumber(),
                documentPage.getTotalPages(),
                documentPage.getSize(),
                documentPage.getTotalElements(),
                documentPage.getSort().toString());
    }
}
