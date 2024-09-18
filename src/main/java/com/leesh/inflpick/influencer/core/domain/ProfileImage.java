package com.leesh.inflpick.influencer.core.domain;

import org.jetbrains.annotations.NotNull;

import java.net.URI;
import java.net.URISyntaxException;

public record ProfileImage(@NotNull URI uri) {

    public static ProfileImage of(@NotNull String profileImageUri) {
        try {
            URI uri = new URI(profileImageUri);
            return new ProfileImage(uri);
        } catch (URISyntaxException e) {
            throw new ProfileImageUriSyntaxException(profileImageUri);
        }
    }
}
