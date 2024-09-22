package com.leesh.inflpick.keyword.core.domain;

import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.regex.Pattern;

public record KeywordColor(@NotNull Color color) {

    private static final Pattern PATTERN = Pattern.compile("^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$");

    private static void validateInput(@NotNull String hexColor) throws HexColorSyntaxException {
        if (!PATTERN.matcher(hexColor).matches()) {
            throw new HexColorSyntaxException("Invalid hex color syntax: " + hexColor);
        }
    }

    public static KeywordColor from(String hexColor) throws HexColorSyntaxException {
        validateInput(hexColor);
        return new KeywordColor(Color.decode(hexColor));
    }

    public String hexColor() {
        return String.format("#%06X", (0xFFFFFF & color.getRGB()));
    }

}
