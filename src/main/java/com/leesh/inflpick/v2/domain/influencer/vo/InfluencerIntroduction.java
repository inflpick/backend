package com.leesh.inflpick.v2.domain.influencer.vo;

import com.leesh.inflpick.v2.domain.influencer.exception.InvalidInfluencerIntroductionException;
import lombok.Getter;

import java.util.Objects;
import java.util.regex.Pattern;

public final class InfluencerIntroduction {

    // InfluencerIntroduction must be between 0 and 1000 characters long
    private static final Pattern PATTERN = Pattern.compile("^.{0,1000}$");
    @Getter
    private final String value;

    private InfluencerIntroduction() {
        this.value = "";
    }

    private InfluencerIntroduction(String value) {
        if (!PATTERN.matcher(value).matches()) {
            throw new InvalidInfluencerIntroductionException("InfluencerIntroduction must be between 0 and 1000 characters long, but was: " + value);
        }
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InfluencerIntroduction that = (InfluencerIntroduction) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    /* Business Logic */
    public static InfluencerIntroduction create(String introduction) {
        return new InfluencerIntroduction(introduction);
    }

    public static InfluencerIntroduction empty() {
        return new InfluencerIntroduction();
    }
}
