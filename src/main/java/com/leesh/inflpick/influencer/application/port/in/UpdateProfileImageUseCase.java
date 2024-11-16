package com.leesh.inflpick.influencer.application.port.in;

import com.leesh.inflpick.influencer.application.exception.InvalidProfileImageException;
import com.leesh.inflpick.influencer.domain.vo.InfluencerId;
import org.springframework.web.multipart.MultipartFile;

public interface UpdateProfileImageUseCase {

    void updateProfileImage(InfluencerId id, MultipartFile profileImage) throws InvalidProfileImageException;
}
