package com.leesh.inflpick.v2.review.domain.vo;

import com.leesh.inflpick.v2.review.domain.exception.ReviewContentsFormatException;

import java.time.Instant;
import java.util.regex.Pattern;

public record ReviewSource(String contents,
                           String url,
                           Instant reviewDate) {

    // Review Contetns must be between 1 and 50000 characters
    private final static Pattern CONTENTS_PATTERN = Pattern.compile("^.{1,50000}$");

    public ReviewSource {
        if (contents == null || contents.isEmpty()) {
            contents = "";
        } else {
            String stripped = contents.strip();
            if (!CONTENTS_PATTERN.matcher(stripped).matches()) {
                throw new ReviewContentsFormatException(contents);
            }
            contents = stripped;
        }
    }

    /* Business Logic */
    public static ReviewSource create(String contents, String url, Instant reviewDate) {
        return new ReviewSource(contents, url, reviewDate);
    }

    public static ReviewSource empty() {
        return new ReviewSource("", "", Instant.MIN);
    }
}
