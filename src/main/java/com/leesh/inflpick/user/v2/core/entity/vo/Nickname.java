package com.leesh.inflpick.user.v2.core.entity.vo;

import com.leesh.inflpick.user.v2.core.entity.exception.InvalidNicknameFormatException;

import java.util.Objects;
import java.util.regex.Pattern;

public final class Nickname {

    public static final int MIN_LENGTH = 1;
    public static final int MAX_LENGTH = 300;
    // Nickname must be between 1 and 50 characters long
    private static final Pattern PATTERN = Pattern.compile("^.{1,50}$");
    private final String value;

    private Nickname(String value) {
        if (!PATTERN.matcher(value).matches()) {
            throw new InvalidNicknameFormatException("Nickname must be between 1 and 50 characters long, but was: " + value);
        }
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Nickname) obj;
        return Objects.equals(this.value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    /* Business Logic */
    public static Nickname create(String name) {
        return new Nickname(name);
    }

    public String value() {
        return value;
    }

}