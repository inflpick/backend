package com.leesh.inflpick.v2.keyword.domain.vo;

import com.leesh.inflpick.v2.keyword.domain.exception.KeywordNameFormatException;

import java.util.regex.Pattern;

public record KeywordName(String name) {

    // Keyword name must be between 1 and 20 characters
    private static final Pattern PATTERN = Pattern.compile("^.{1,20}$");

    public KeywordName {
        if (name == null || name.isBlank()) {
            name = "";
        } else {
            if (!PATTERN.matcher(name).matches()) {
                throw new KeywordNameFormatException(name);
            }
            name = name.trim();
        }
    }

    /* Business Logic */
    public static KeywordName create(String value) {
        return new KeywordName(value);
    }

    public static KeywordName empty() {
        return new KeywordName("");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        KeywordName that = (KeywordName) obj;
        return name.equals(that.name);
    }

    public boolean equals(String name) {
        return this.name.equals(name);
    }
}
