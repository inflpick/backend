package com.leesh.inflpick.influencer.port.in;

import com.leesh.inflpick.influencer.core.domain.*;

public interface InfluencerService {

    void create(InfluencerName name,
                InfluencerDescription description,
                ProfileImage profileImage,
                SocialMediaLinks socialMediaLinks);

    Influencer getById(InfluencerId id);

}
