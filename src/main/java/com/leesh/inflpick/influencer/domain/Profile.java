package com.leesh.inflpick.influencer.domain;

import lombok.NonNull;

import java.net.URI;
import java.util.Objects;

public record Profile(@NonNull String name,
                      @NonNull URI profileImageUri,
                      @NonNull String description) {

    public Profile {
        Objects.requireNonNull(name, "name must be provided");
        Objects.requireNonNull(profileImageUri, "profileImageUri must be provided");
        Objects.requireNonNull(description, "description must be provided");

        if (name.isBlank()) {
            throw new IllegalArgumentException("name must not be blank");
        }

        if (description.isBlank()) {
            throw new IllegalArgumentException("description must not be blank");
        }
    }

}
