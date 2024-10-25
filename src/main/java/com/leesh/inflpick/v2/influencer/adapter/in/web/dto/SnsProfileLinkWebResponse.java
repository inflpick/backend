package com.leesh.inflpick.v2.influencer.adapter.in.web.dto;

import com.leesh.inflpick.v2.influencer.adapter.in.web.docs.SnsProfileLinkResponseDocs;
import com.leesh.inflpick.v2.influencer.domain.vo.SnsProfileLink;

public record SnsProfileLinkWebResponse(String platform, String url) implements SnsProfileLinkResponseDocs {
    public static SnsProfileLinkWebResponse from(SnsProfileLink snsProfileLink) {
        return new SnsProfileLinkWebResponse(
                snsProfileLink.getPlatform().name(),
                snsProfileLink.getUrl());
    }
}
