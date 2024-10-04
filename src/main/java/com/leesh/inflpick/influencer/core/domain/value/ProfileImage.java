package com.leesh.inflpick.influencer.core.domain.value;

import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;

public record ProfileImage(@NotNull String imagePath) {

    public static final ProfileImage EMPTY = new ProfileImage("");

    public static ProfileImage from(@NotNull String imagePath) {
        return new ProfileImage(imagePath);
    }

    public Path basePath(String id) {
        return Path.of("/influencers", id, "/profile-image");
    }
}
