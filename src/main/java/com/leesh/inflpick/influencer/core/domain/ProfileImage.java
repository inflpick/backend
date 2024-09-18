package com.leesh.inflpick.influencer.core.domain;

import org.jetbrains.annotations.NotNull;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.MessageFormat;

public record ProfileImage(@NotNull URI uri) {

    public final static MessageFormat ERROR_MESSAGE_FORMAT = new MessageFormat("올바른 URI 형식으로 입력해주세요. 입력 값: {0}");

    public static ProfileImage of(@NotNull String profileImageUri) {
        try {
            URI uri = new URI(profileImageUri);
            return new ProfileImage(uri);
        } catch (URISyntaxException e) {
            throw new ProfileImageUriSyntaxException(ERROR_MESSAGE_FORMAT.format(new Object[]{profileImageUri}));
        }
    }
}
