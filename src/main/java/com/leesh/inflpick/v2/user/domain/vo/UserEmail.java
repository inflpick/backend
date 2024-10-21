package com.leesh.inflpick.v2.user.domain.vo;

import com.leesh.inflpick.v2.user.domain.exception.InvalidEmailFormatException;

import java.util.Objects;
import java.util.regex.Pattern;

public final class UserEmail {

    public static final Pattern PATTERN = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
    private final String value;

    private UserEmail(String value) {
        if (!PATTERN.matcher(value).matches()) {
            throw new InvalidEmailFormatException("Email must be valid, but was: " + value);
        }
        this.value = value;
    }

    private UserEmail() {
        this.value = "";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (UserEmail) obj;
        return Objects.equals(this.value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public static UserEmail create(String email) {
        return new UserEmail(email);
    }

    public String value() {
        return value;
    }

    public static UserEmail empty() {
        return new UserEmail();
    }

}
