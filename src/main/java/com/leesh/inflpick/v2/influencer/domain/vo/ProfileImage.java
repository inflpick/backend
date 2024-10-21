package com.leesh.inflpick.v2.influencer.domain.vo;

import lombok.Getter;

import java.nio.file.Path;
import java.util.Objects;

@Getter
public final class ProfileImage {

    private final String path;

    private ProfileImage() {
        this.path = "";
    }

    private ProfileImage(String path) {
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfileImage that = (ProfileImage) o;
        return Objects.equals(path, that.path);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(path);
    }

    /* Business Logic */
    public static ProfileImage create(String path) {
        return new ProfileImage(path);
    }

    public static ProfileImage empty() {
        return new ProfileImage();
    }

    public boolean isEmpty() {
        return path.isEmpty();
    }

    public Path getBasePath(InfluencerId id) {
        return Path.of("/influencers", id.getValue(), "/profile-image");
    }
}
