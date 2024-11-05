package com.leesh.inflpick.v2.product.adapter.in.web.dto;

import com.leesh.inflpick.v2.common.application.service.RequiredFieldsValidator;
import com.leesh.inflpick.v2.product.adapter.in.web.exception.NotSupportOnlineStorePlatformException;
import com.leesh.inflpick.v2.product.domain.vo.OnlineStoreLink;
import com.leesh.inflpick.v2.product.domain.vo.OnlineStorePlatform;

public record OnlineStoreLinkWebRequest(String platform, String url) {

    public OnlineStoreLinkWebRequest(String platform, String url) {
        RequiredFieldsValidator.validate(platform, url);
        this.platform = platform.strip();
        this.url = url.strip();
    }

    public OnlineStoreLink toEntity() {
        OnlineStorePlatform platform;
        try {
            platform = OnlineStorePlatform.valueOf(this.platform);
        } catch (IllegalArgumentException e) {
            throw new NotSupportOnlineStorePlatformException("Not support online store platform: " + this.platform);
        }
        return OnlineStoreLink.create(platform, this.url);
    }
}
