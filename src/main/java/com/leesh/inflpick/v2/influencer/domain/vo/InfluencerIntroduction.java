package com.leesh.inflpick.v2.influencer.domain.vo;

import com.leesh.inflpick.v2.influencer.domain.exception.InfluencerIntroductionFormatException;

import java.util.regex.Pattern;

public record InfluencerIntroduction(String introduction) {

    // InfluencerIntroduction must be between 0 and 1000 characters long
    private static final Pattern PATTERN = Pattern.compile("^.{0,1000}$");

    public InfluencerIntroduction {
        if (introduction == null || introduction.isEmpty()) {
            introduction = "";
        } else {
            String stripped = introduction.strip();
            if (!PATTERN.matcher(stripped).matches()) {
                throw new InfluencerIntroductionFormatException(introduction);
            }
            introduction = stripped;
        }
    }

    /* Business Logic */
    public static InfluencerIntroduction create(String introduction) {
        return new InfluencerIntroduction(introduction);
    }

    public static InfluencerIntroduction empty() {
        return new InfluencerIntroduction("");

    }
}
