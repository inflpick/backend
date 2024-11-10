package com.leesh.inflpick.v2.influencer.application.dto;

import com.leesh.inflpick.v2.influencer.adapter.out.docs.swagger.dto.SnsProfileLinkResponseDocs;
import com.leesh.inflpick.v2.influencer.domain.vo.SnsProfileLink;

public record SnsProfileLinkResponse(String platform, String url) implements SnsProfileLinkResponseDocs {
    public static SnsProfileLinkResponse create(SnsProfileLink snsProfileLink) {
        return new SnsProfileLinkResponse(
                snsProfileLink.platform().name(),
                snsProfileLink.url());
    }
}
