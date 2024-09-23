package com.leesh.inflpick.influencer.port.in;

import com.leesh.inflpick.influencer.core.service.InfluencerCommand;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.multipart.MultipartFile;

public interface InfluencerCommandService {

    String create(@NotNull InfluencerCommand command);

    void update(String id, InfluencerCommand command);

    void delete(String id);

    void updateProfileImage(String id, MultipartFile profileImage);
}
