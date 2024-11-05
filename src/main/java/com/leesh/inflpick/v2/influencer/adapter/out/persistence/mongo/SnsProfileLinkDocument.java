package com.leesh.inflpick.v2.influencer.adapter.out.persistence.mongo;

import com.leesh.inflpick.v2.influencer.domain.vo.SnsPlatform;
import com.leesh.inflpick.v2.influencer.domain.vo.SnsProfileLink;

public record SnsProfileLinkDocument(String platform,
                                     String url) {

    static SnsProfileLinkDocument from(SnsProfileLink snsProfileLink) {
        String platform = snsProfileLink.platform().name();
        String url = snsProfileLink.url();
        return new SnsProfileLinkDocument(platform, url);
    }

    SnsProfileLink toEntity() {
        SnsPlatform platform = SnsPlatform.valueOf(this.platform);
        return SnsProfileLink.create(platform, url);
    }

}
