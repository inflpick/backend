package com.leesh.inflpick.v2.keyword.domain.exception;

public class KeywordHexColorException extends RuntimeException {
    public KeywordHexColorException(String color) {
        super("Keyword Color must be hex color code, but was: " + color);
    }
}
