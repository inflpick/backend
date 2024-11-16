package com.leesh.inflpick.influencer.application.dto;

import com.leesh.inflpick.influencer.adapter.out.docs.swagger.dto.SnsProfileLinkResponseDocs;
import com.leesh.inflpick.influencer.domain.vo.SnsProfileLink;

public record SnsProfileLinkResponse(String platform, String url) implements SnsProfileLinkResponseDocs {
    public static SnsProfileLinkResponse create(SnsProfileLink snsProfileLink) {
        return new SnsProfileLinkResponse(
                snsProfileLink.platform().name(),
                snsProfileLink.url());
    }
}
