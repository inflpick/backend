package com.leesh.inflpick.influencer.core.service;

import com.leesh.inflpick.common.port.out.StorageService;
import com.leesh.inflpick.common.port.out.UuidHolder;
import com.leesh.inflpick.influencer.core.domain.Influencer;
import com.leesh.inflpick.influencer.core.domain.value.Keywords;
import com.leesh.inflpick.influencer.port.in.InfluencerCommandService;
import com.leesh.inflpick.influencer.port.in.InfluencerQueryService;
import com.leesh.inflpick.influencer.port.out.InfluencerRepository;
import com.leesh.inflpick.keyword.port.out.KeywordRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.Set;

@Builder
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class InfluencerServiceImpl implements InfluencerQueryService, InfluencerCommandService {

    private final UuidHolder uuidHolder;
    private final InfluencerRepository influencerRepository;
    private final KeywordRepository keywordRepository;
    private final StorageService storageService;

    @Override
    public Influencer getById(String id) {
        return influencerRepository.getById(id);
    }

    @Transactional
    @Override
    public String create(@NotNull InfluencerCommand command) {

        Set<String> keywordIds = command.keywordIds();
        Keywords keywords = keywordRepository.getAllByIds(keywordIds);

        Influencer influencer = command.toEntity(uuidHolder);
        influencer.addKeywords(keywords);

        return influencerRepository.save(influencer);
    }

    @Transactional
    @Override
    public void update(String id, InfluencerCommand command) {
        Influencer influencer = influencerRepository.getById(id);
        Set<String> keywordIds = command.keywordIds();
        Keywords keywords = keywordRepository.getAllByIds(keywordIds);
        influencer.update(command, keywords);
        influencerRepository.save(influencer);
    }

    @Override
    public void delete(String id) {
        influencerRepository.deleteById(id);
    }

    @Override
    public void updateProfileImage(String id, MultipartFile profileImage) {
        Influencer influencer = influencerRepository.getById(id);
        Path basePath = influencer.getProfileImageBasePath();
        String uploadPath = storageService.upload(profileImage, basePath);
        influencer.registerProfileImagePath(uploadPath);
        influencerRepository.save(influencer);
    }
}
