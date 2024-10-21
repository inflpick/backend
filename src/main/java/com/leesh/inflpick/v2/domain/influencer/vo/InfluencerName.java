package com.leesh.inflpick.v2.domain.influencer.vo;

import com.leesh.inflpick.v2.domain.influencer.exception.InvalidInfluencerNameFormatException;
import lombok.Getter;

import java.util.Objects;
import java.util.regex.Pattern;

public final class InfluencerName {

    // InfluencerName must be between 1 and 300 characters long
    private static final Pattern PATTERN = Pattern.compile("^.{1,300}$");
    @Getter
    private final String value;

    private InfluencerName(String value) {
        if (!PATTERN.matcher(value).matches()) {
            throw new InvalidInfluencerNameFormatException("InfluencerName must be between 1 and 300 characters long, but was: " + value);
        }
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (InfluencerName) obj;
        return Objects.equals(this.value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    /* Business Logic */
    public static InfluencerName create(String name) {
        return new InfluencerName(name);
    }

}
