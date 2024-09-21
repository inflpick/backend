package com.leesh.inflpick.influencer.core.domain.value;

import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;

public record ProfileImage(@NotNull String imagePath) {

    public static ProfileImage of(@NotNull String profileImagePath) {
        return new ProfileImage(profileImagePath);
    }

    public Path basePath(String uuid) {
        return Path.of("/influencers", uuid, "/profile-image");
    }
}
