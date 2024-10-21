package com.leesh.inflpick.v2.influencer.domain.vo;

import com.leesh.inflpick.v2.influencer.domain.exception.InvalidInfluencerDescriptionException;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.regex.Pattern;

public final class InfluencerDescription {

    // InfluencerDescription must be between 1 and 50000 characters long
    private static final Pattern PATTERN = Pattern.compile("^.{1,50000}$");
    @Getter
    private final String value;

    private InfluencerDescription() {
        this.value = "";
    }

    private InfluencerDescription(@NotNull String value) {
        if (!PATTERN.matcher(value).matches()) {
            throw new InvalidInfluencerDescriptionException("InfluencerDescription must be between 1 and 50000 characters long, but was: " + value);
        }
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InfluencerDescription that = (InfluencerDescription) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    /* Business Logic */
    public static InfluencerDescription create(String value) {
        return new InfluencerDescription(value);
    }

    public static InfluencerDescription empty() {
        return new InfluencerDescription();
    }
}
