package com.leesh.inflpick.influencer.core.domain;

import org.jetbrains.annotations.NotNull;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;

public record ProfileImage(@NotNull URI uri) {

    public static ProfileImage of(@NotNull String profileImageUri) {
        try {
            URI uri = new URI(profileImageUri);
            return new ProfileImage(uri);
        } catch (URISyntaxException e) {
            throw new ProfileImageUriSyntaxException(profileImageUri);
        }
    }

    public Path basePath(String uuid) {
        return Path.of("/influencers", uuid, "/profile-images");
    }
}
