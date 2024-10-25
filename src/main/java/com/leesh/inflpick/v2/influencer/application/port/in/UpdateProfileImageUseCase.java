package com.leesh.inflpick.v2.influencer.application.port.in;

import com.leesh.inflpick.v2.influencer.domain.vo.InfluencerId;
import org.springframework.web.multipart.MultipartFile;

public interface UpdateProfileImageUseCase {

    void updateProfileImage(InfluencerId id, MultipartFile profileImage);
}
