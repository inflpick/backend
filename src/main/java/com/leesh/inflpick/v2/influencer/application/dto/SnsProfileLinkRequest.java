package com.leesh.inflpick.v2.influencer.application.dto;

import com.leesh.inflpick.v2.influencer.adapter.out.docs.swagger.dto.SnsProfileLinkRequestDocs;
import com.leesh.inflpick.v2.influencer.domain.exception.NotSupportedSnsPlatformException;
import com.leesh.inflpick.v2.influencer.domain.vo.SnsPlatform;
import com.leesh.inflpick.v2.influencer.domain.vo.SnsProfileLink;

public record SnsProfileLinkRequest(String platform, String url) implements SnsProfileLinkRequestDocs {

    public SnsProfileLink toEntity() {
        try {
            SnsPlatform platform = SnsPlatform.valueOf(this.platform);
            return SnsProfileLink.create(platform, url);
        } catch (IllegalArgumentException e) {
            throw new NotSupportedSnsPlatformException(platform);
        }
    }
}
