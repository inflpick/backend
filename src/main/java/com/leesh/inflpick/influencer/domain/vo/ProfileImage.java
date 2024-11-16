package com.leesh.inflpick.influencer.domain.vo;

import java.nio.file.Path;

public record ProfileImage(String path) {

    /* Business Logic */
    public static ProfileImage create(String path) {
        return new ProfileImage(path);
    }

    public static ProfileImage empty() {
        return new ProfileImage("");
    }

    public boolean isEmpty() {
        return path.isEmpty();
    }

    public Path getBasePath(InfluencerId id) {
        return Path.of("/influencers", id.id(), "/profile-image");
    }
}
