package com.leesh.inflpick.v2.product.adapter.in.web.exception;

public class NotSupportOnlineStorePlatformException extends IllegalArgumentException {
    public NotSupportOnlineStorePlatformException(String platform) {
        super("Not support online store platform: " + platform);
    }
}
