package com.leesh.inflpick.v2.influencer.adapter.out.persistence.mongo;

import com.leesh.inflpick.v2.influencer.domain.vo.SnsPlatform;
import com.leesh.inflpick.v2.influencer.domain.vo.SnsProfileLink;

public class SnsProfileLinkDocument {

    private final String platform;
    private final String url;

    private SnsProfileLinkDocument(String platform, String url) {
        this.platform = platform;
        this.url = url;
    }

    static SnsProfileLinkDocument from(SnsProfileLink snsProfileLink) {
        String platform = snsProfileLink.getPlatform().name();
        String url = snsProfileLink.getUrl();
        return new SnsProfileLinkDocument(platform, url);
    }

    SnsProfileLink toEntity() {
        SnsPlatform platform = SnsPlatform.valueOf(this.platform);
        return SnsProfileLink.create(platform, url);
    }

}
