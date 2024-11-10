package com.leesh.inflpick.v2.influencer.application.service;

import com.leesh.inflpick.v2.common.application.exception.FileFormatException;
import com.leesh.inflpick.v2.common.application.port.out.storage.StoragePort;
import com.leesh.inflpick.v2.influencer.application.exception.InfluencerNotFoundException;
import com.leesh.inflpick.v2.influencer.application.exception.InvalidProfileImageException;
import com.leesh.inflpick.v2.influencer.application.port.in.UpdateProfileImageUseCase;
import com.leesh.inflpick.v2.influencer.application.port.out.CommandInfluencerPort;
import com.leesh.inflpick.v2.influencer.application.port.out.QueryInfluencerPort;
import com.leesh.inflpick.v2.influencer.domain.Influencer;
import com.leesh.inflpick.v2.influencer.domain.vo.InfluencerId;
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
    public void updateProfileImage(InfluencerId id, MultipartFile profileImage) throws InvalidProfileImageException {
        Influencer influencer = queryInfluencerPort.query(id)
                .orElseThrow(() -> new InfluencerNotFoundException(id));
        Path basePath = influencer.profileImage().getBasePath(id);
        URL uploadUrl = uploadProfileImage(profileImage, basePath);
        String profileImagePath = uploadUrl.getPath();
        Influencer updatedProfileImageInfluencer = influencer.updateProfileImage(profileImagePath);
        commandInfluencerPort.save(updatedProfileImageInfluencer);
    }

    private URL uploadProfileImage(MultipartFile profileImage, Path basePath) {
        URL uploadUrl;
        try {
           uploadUrl = storagePort.upload(profileImage, basePath);
        } catch (FileFormatException e) {
            throw new InvalidProfileImageException(profileImage.getOriginalFilename());
        }
        return uploadUrl;
    }
}
