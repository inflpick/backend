package com.leesh.inflpick.keyword.core.domain;

import com.leesh.inflpick.influencer.core.domain.CustomConstraintViolationExceptionBase;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.text.MessageFormat;
import java.util.regex.Pattern;

public record KeywordColor(@NotNull Color color) {

    private static final Pattern PATTERN = Pattern.compile("^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$");
    public static final MessageFormat ERROR_MESSAGE_FORMAT;

    static {
        String pattern = "키워드 색상은 16진수 형식으로 입력해야 합니다. 입력 값: {0}";
        ERROR_MESSAGE_FORMAT = new MessageFormat(pattern);
    }

    private static void validateInput(@NotNull String hexColor) throws CustomConstraintViolationExceptionBase {
        if (!PATTERN.matcher(hexColor).matches()) {
            throw new HexColorSyntaxException(ERROR_MESSAGE_FORMAT.format(new Object[]{hexColor}));
        }
    }

    public static KeywordColor from(String hexColor) throws CustomConstraintViolationExceptionBase {
        validateInput(hexColor);
        return new KeywordColor(Color.decode(hexColor));
    }

    public String hexColor() {
        return String.format("#%06X", (0xFFFFFF & color.getRGB()));
    }

}
