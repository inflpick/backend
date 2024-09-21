package com.leesh.inflpick.influencer.port.in;

import com.leesh.inflpick.influencer.core.domain.Influencer;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.multipart.MultipartFile;

public interface InfluencerCreateService {

    Influencer create(@NotNull InfluencerCreateCommand command,
                      @NotNull MultipartFile multipartFile);

}
