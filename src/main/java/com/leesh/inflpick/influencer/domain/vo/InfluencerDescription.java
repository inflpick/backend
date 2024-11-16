package com.leesh.inflpick.influencer.domain.vo;

import com.leesh.inflpick.influencer.domain.exception.InfluencerDescriptionFormatException;

import java.util.regex.Pattern;

public record InfluencerDescription(String description) {

    // InfluencerDescription must be between 1 and 50000 characters long
    private static final Pattern PATTERN = Pattern.compile("^[\\s\\S]{1,50000}$");

    public InfluencerDescription {
        if (description == null || description.isEmpty()) {
            description = "";
        } else {
            String stripped = description.strip();
            if (!PATTERN.matcher(stripped).matches()) {
                throw new InfluencerDescriptionFormatException(description);
            }
            description = stripped;
        }
    }

    /* Business Logic */
    public static InfluencerDescription create(String value) {
        return new InfluencerDescription(value);
    }

    public static InfluencerDescription empty() {
        return new InfluencerDescription("");
    }
}
