package com.leesh.inflpick.v2.influencer.domain.exception;

public class InfluencerIntroductionFormatException extends IllegalArgumentException {
    public InfluencerIntroductionFormatException(String introduction) {
        super("InfluencerIntroduction must be between 0 and 1000 characters long" + introduction.length());
    }
}
