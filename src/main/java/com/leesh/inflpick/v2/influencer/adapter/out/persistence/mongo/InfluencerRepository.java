package com.leesh.inflpick.v2.influencer.adapter.out.persistence.mongo;

import com.leesh.inflpick.common.adapter.out.persistence.SpringDataPageRequestConverter;
import com.leesh.inflpick.product.port.ProductSortProperty;
import com.leesh.inflpick.v2.influencer.application.port.out.CommandInfluencerPort;
import com.leesh.inflpick.v2.influencer.application.port.out.QueryInfluencerPort;
import com.leesh.inflpick.v2.influencer.domain.Influencer;
import com.leesh.inflpick.v2.influencer.domain.vo.InfluencerId;
import com.leesh.inflpick.v2.shared.application.dto.PageResponse;
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

    private InfluencerMongoRepository influencerMongoRepository;

    @Override
    public InfluencerId save(Influencer influencer) {
        InfluencerDocument document = InfluencerDocument.from(influencer);
        InfluencerDocument saved = influencerMongoRepository.save(document);
        return InfluencerId.create(saved.getId());
    }

    @Override
    public void delete(InfluencerId id) {
        influencerMongoRepository.deleteById(id.getValue());
    }

    @Override
    public Optional<Influencer> query(InfluencerId id) {
        return influencerMongoRepository.findById(id.getValue())
                .map(InfluencerDocument::toEntity);
    }

    @Override
    public PageResponse<Influencer> query(com.leesh.inflpick.v2.shared.application.dto.PageRequest request) {
        PageRequest pageRequest = SpringDataPageRequestConverter.convert(request, () -> Arrays.stream(ProductSortProperty.values())
                .map(ProductSortProperty::getValue)
                .toList());
        Page<InfluencerDocument> documentPage = influencerMongoRepository.findAll(pageRequest);
        List<Influencer> influencers = documentPage.getContent().stream().map(InfluencerDocument::toEntity).toList();
        String sortProperties = documentPage.getSort().toString();
        return new PageResponse<>(influencers,
                documentPage.getNumber(),
                documentPage.getTotalPages(),
                documentPage.getSize(),
                documentPage.getTotalElements(),
                sortProperties);
    }
}
