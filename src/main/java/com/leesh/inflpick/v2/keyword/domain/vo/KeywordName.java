package com.leesh.inflpick.v2.keyword.domain.vo;

import com.leesh.inflpick.v2.keyword.domain.exception.KeywordNameFormatException;
import lombok.Getter;

import java.util.Objects;
import java.util.regex.Pattern;

@Getter
public final class KeywordName {

    // Keyword name must be between 1 and 20 characters
    private static final Pattern PATTERN = Pattern.compile("^.{1,20}$");
    private final String value;

    private KeywordName(String value) {
        if (!PATTERN.matcher(value).matches()) {
            throw new KeywordNameFormatException("Keyword name must be between 1 and 20 characters long, but was: " + value);
        }
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KeywordName that = (KeywordName) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    /* Business Logic */
    public static KeywordName create(String value) {
        return new KeywordName(value);
    }
}
