package com.leesh.inflpick.user.domain.vo;

import java.util.regex.Pattern;

public record UserEmail(String email) {

    public static final Pattern PATTERN = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");

    public UserEmail {
        if (email != null && !PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException("Invalid email format");
        }
    }

    public static UserEmail create(String email) {
        return new UserEmail(email);
    }

    public static UserEmail empty() {
        return new UserEmail("");
    }

}
