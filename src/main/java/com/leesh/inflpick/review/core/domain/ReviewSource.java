package com.leesh.inflpick.review.core.domain;

import jakarta.validation.constraints.NotNull;

import java.time.Instant;

public record ReviewSource(@NotNull String contents,
                           @NotNull String uri,
                           @NotNull Instant reviewedDate) {

    public static final Integer CONTENTS_MIN_LENGTH = 1;
    public static final Integer CONTENTS_MAX_LENGTH = 30000;
    public static final Integer URI_MIN_LENGTH = 1;
    public static final Integer URI_MAX_LENGTH = 2000;

    public ReviewSource {
        validateInput(contents, uri);
    }

    private void validateInput(String contents, String uri) {
        if (contents.length() < CONTENTS_MIN_LENGTH || contents.length() > CONTENTS_MAX_LENGTH) {
            throw new ReviewContentsValidationFailedException(contents);
        }
        if (uri.length() < URI_MIN_LENGTH || uri.length() > URI_MAX_LENGTH) {
            throw new ReviewUriValidationFailedException(uri);
        }
    }

    public static ReviewSource of(String contents, String uri, Instant reviewedDate) {
        return new ReviewSource(contents, uri, reviewedDate);
    }
}
