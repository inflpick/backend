package com.leesh.inflpick.v2.influencer.domain.exception;

public class InfluencerNameFormatException extends IllegalArgumentException {
    public InfluencerNameFormatException(String name) {
        super("InfluencerName must be between 1 and 300 characters long, but was: " + name.length());
    }
}
