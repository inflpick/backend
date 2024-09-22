package com.leesh.inflpick.influencer.port.in;

import org.jetbrains.annotations.NotNull;
import org.springframework.web.multipart.MultipartFile;

public interface InfluencerCreateService {

    String create(@NotNull InfluencerCreateCommand command,
                      @NotNull MultipartFile multipartFile);

}
