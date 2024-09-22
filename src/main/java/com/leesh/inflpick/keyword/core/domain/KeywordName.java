package com.leesh.inflpick.keyword.core.domain;

import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

public record KeywordName(@NotNull String name) {

    public static final int MIN_LENGTH = 1;
    public static final int MAX_LENGTH = 30;
    private static final Pattern PATTERN = Pattern.compile("^[a-zA-Z0-9가-힣\\s]{%d,%d}$".formatted(MIN_LENGTH, MAX_LENGTH));

    public KeywordName {
        validateInput(name);
    }

    public static KeywordName from(String keyword) {
        return new KeywordName(keyword);
    }

    private void validateInput(@NotNull String name) throws KeywordNameValidationFailedException {
        if (!PATTERN.matcher(name).matches()) {
            throw new KeywordNameValidationFailedException(name);
        }
    }
}
