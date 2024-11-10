package com.leesh.inflpick.v2.keyword.domain.vo;

import com.leesh.inflpick.v2.keyword.domain.exception.KeywordHexColorException;

import java.util.regex.Pattern;

public record KeywordColor(String hexColor) {

    // Keyword Color must be hex color code
    private static final Pattern PATTERN = Pattern.compile("^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$");

    public KeywordColor {
        if (!PATTERN.matcher(hexColor).matches()) {
            throw new KeywordHexColorException(hexColor);
        }
    }

    /* Business Logic */
    public static KeywordColor create(String value) {
        return new KeywordColor(value);
    }

    public static KeywordColor withDefault() {
        return new KeywordColor("#FFFFFF");
    }
}
