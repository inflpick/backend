package com.leesh.inflpick.v2.keyword.domain.vo;

import lombok.Getter;

import java.util.Objects;
import java.util.regex.Pattern;

@Getter
public final class KeywordColor {

    // Keyword Color must be hex color code
    private static final Pattern PATTERN = Pattern.compile("^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$");
    private final String value;

    private KeywordColor() {
        this.value = "#FFFFFF";
    }

    private KeywordColor(String value) {
        if (!PATTERN.matcher(value).matches()) {
            throw new IllegalArgumentException("Keyword Color must be hex color code, but was: " + value);
        }
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KeywordColor that = (KeywordColor) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    /* Business Logic */
    public static KeywordColor create(String value) {
        return new KeywordColor(value);
    }

    public static KeywordColor withDefault() {
        return new KeywordColor();
    }
}
