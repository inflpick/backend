package com.leesh.inflpick.influencer.core.service;

import com.leesh.inflpick.common.port.out.UuidHolder;
import com.leesh.inflpick.influencer.core.domain.Influencer;
import com.leesh.inflpick.influencer.core.domain.value.Keywords;
import com.leesh.inflpick.influencer.port.in.InfluencerCreateCommand;
import com.leesh.inflpick.influencer.port.in.InfluencerCreateService;
import com.leesh.inflpick.influencer.port.in.InfluencerReadService;
import com.leesh.inflpick.influencer.port.out.InfluencerRepository;
import com.leesh.inflpick.influencer.port.out.StorageService;
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
public class InfluencerServiceImpl implements InfluencerReadService, InfluencerCreateService {

    private final UuidHolder uuidHolder;
    private final InfluencerRepository influencerRepository;
    private final KeywordRepository keywordRepository;
    private final StorageService storageService;

    @Override
    public Influencer getByUuid(String uuid) {
        return influencerRepository.getByUuid(uuid);
    }

    @Transactional
    @Override
    public String create(@NotNull InfluencerCreateCommand command,
                         @NotNull MultipartFile profileImage) {

        Set<String> keywordIds = command.keywordUuids();
        Keywords keywords = keywordRepository.getAllByUuids(keywordIds);

        Influencer influencer = command.toEntity(uuidHolder);
        influencer.addKeywords(keywords);

        // 프로필 이미지 업로드
        Path basePath = influencer.getProfileImageBasePath();
        String uploadPath = storageService.upload(profileImage, basePath);
        influencer.registerProfileImage(uploadPath);

        return influencerRepository.save(influencer)
                .getUuid();
    }
}
