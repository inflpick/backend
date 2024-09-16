package com.leesh.inflpick.influencer.core.service;

import com.leesh.inflpick.common.port.out.UuidHolder;
import com.leesh.inflpick.influencer.core.domain.*;
import com.leesh.inflpick.influencer.port.in.InfluencerCreateCommand;
import com.leesh.inflpick.influencer.port.in.InfluencerCreateService;
import com.leesh.inflpick.influencer.port.in.InfluencerService;
import com.leesh.inflpick.influencer.port.out.InfluencerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class InfluencerServiceImpl implements InfluencerService, InfluencerCreateService {

    private final UuidHolder uuidHolder;
    private final InfluencerRepository influencerRepository;

    @Override
    public void create(InfluencerName name,
                       InfluencerDescription description,
                       ProfileImage profileImage,
                       SocialMediaLinks socialMediaLinks) {

        InfluencerId id = new InfluencerId(uuidHolder.uuid());

        Influencer newInfluencer = Influencer.builder()
                .id(id)
                .name(name)
                .description(description)
                .profileImage(profileImage)
                .socialMediaLinks(socialMediaLinks)
                .build();

        influencerRepository.save(newInfluencer);
    }

    @Override
    public Influencer getById(InfluencerId id) {
        return influencerRepository.findById(id);
    }

    @Override
    public Influencer create(InfluencerCreateCommand command) {
        Influencer influencer = command.toEntity(uuidHolder);
        influencerRepository.save(influencer);
        return influencerRepository.findById(
                new InfluencerId(influencer.getId())
        );
    }
}
