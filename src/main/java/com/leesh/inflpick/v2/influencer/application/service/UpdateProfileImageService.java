package com.leesh.inflpick.v2.influencer.application.service;

import com.leesh.inflpick.v2.influencer.application.exception.InfluencerNotFoundException;
import com.leesh.inflpick.v2.influencer.application.exception.ProfileImageFormatException;
import com.leesh.inflpick.v2.influencer.application.port.in.UpdateProfileImageUseCase;
import com.leesh.inflpick.v2.influencer.application.port.out.CommandInfluencerPort;
import com.leesh.inflpick.v2.influencer.application.port.out.QueryInfluencerPort;
import com.leesh.inflpick.v2.influencer.domain.Influencer;
import com.leesh.inflpick.v2.influencer.domain.vo.InfluencerId;
import com.leesh.inflpick.v2.shared.application.exception.FileFormatException;
import com.leesh.inflpick.v2.shared.application.port.out.storage.StoragePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.nio.file.Path;

@RequiredArgsConstructor
@Transactional
@Service
public class UpdateProfileImageService implements UpdateProfileImageUseCase {

    private final StoragePort storagePort;
    private final QueryInfluencerPort queryInfluencerPort;
    private final CommandInfluencerPort commandInfluencerPort;

    @Override
    public void updateProfileImage(InfluencerId id, MultipartFile profileImage) {
        Influencer influencer = queryInfluencerPort.query(id)
                .orElseThrow(() -> new InfluencerNotFoundException("Influencer not found, id: %s".formatted(id)));
        Path basePath = influencer.getProfileImage().getBasePath(id);
        URL uploadUrl = uploadProfileImage(profileImage, basePath);
        String profileImagePath = uploadUrl.getPath();
        influencer.updateProfileImage(profileImagePath);
        commandInfluencerPort.save(influencer);
    }

    private URL uploadProfileImage(MultipartFile profileImage, Path basePath) {
        URL uploadUrl;
        try {
           uploadUrl = storagePort.upload(profileImage, basePath);
        } catch (FileFormatException e) {
            throw new ProfileImageFormatException("Profile image format is not supported, file: %s".formatted(profileImage.getOriginalFilename()));
        }
        return uploadUrl;
    }
}
