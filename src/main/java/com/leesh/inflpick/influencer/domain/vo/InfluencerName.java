package com.leesh.inflpick.influencer.domain.vo;

import com.leesh.inflpick.influencer.domain.exception.InfluencerNameFormatException;

import java.util.regex.Pattern;

public record InfluencerName(String name) {

    // InfluencerName must be between 1 and 300 characters long
    private static final Pattern PATTERN = Pattern.compile("^.{0,300}$");

    public InfluencerName {
        if (name == null || name.isEmpty()) {
            name = "";
        } else {
            String stripped = name.strip();
            if (!PATTERN.matcher(stripped).matches()) {
                throw new InfluencerNameFormatException(name);
            }
            name = stripped;
        }
    }

    /* Business Logic */
    public static InfluencerName create(String name) {
        return new InfluencerName(name);
    }

}
