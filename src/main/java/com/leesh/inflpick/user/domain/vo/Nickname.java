package com.leesh.inflpick.user.domain.vo;

import com.leesh.inflpick.user.domain.exception.InvalidNicknameFormatException;

import java.util.Objects;
import java.util.regex.Pattern;

public record Nickname(String nickname) {

    // Nickname must be between 1 and 50 characters long
    private static final Pattern PATTERN = Pattern.compile("^.{1,50}$");

    public Nickname {
        if (Objects.isNull(nickname) || !PATTERN.matcher(nickname).matches()) {
            throw new InvalidNicknameFormatException(nickname);
        }
    }

    /* Business Logic */
    public static Nickname create(String name) {
        return new Nickname(name);
    }

}