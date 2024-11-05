package com.leesh.inflpick.v2.keyword.domain.exception;

public class KeywordNameFormatException extends IllegalArgumentException {
    public KeywordNameFormatException(String name) {
        super("Keyword Name must be between 1 and 20 characters, but was: " + name);
    }
}
