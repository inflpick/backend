package com.leesh.inflpick.user.core.domain;

import com.leesh.inflpick.influencer.core.domain.exception.InfluencerNameValidationFailedException;

import java.util.Objects;
import java.util.regex.Pattern;

public final class Nickname {

    public static final int MIN_LENGTH = 1;
    public static final int MAX_LENGTH = 300;
    private static final Pattern PATTERN = Pattern.compile("^.{1,50}$");
    private final String name;

    private Nickname(String name) {
        if (!PATTERN.matcher(name).matches()) {
            throw new
        }
        this.name = name;
    }

    private void validateInput(String name) throws UsernameValidationFailedException {
        if (name.isBlank() || name.isEmpty() || name.length() > MAX_LENGTH) {
            throw new InfluencerNameValidationFailedException(name);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Nickname) obj;
        return Objects.equals(this.name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    /* Business Logic */
    public static Nickname from(String name) {
        return new Nickname(name);
    }

    public String name() {
        return name;
    }

}