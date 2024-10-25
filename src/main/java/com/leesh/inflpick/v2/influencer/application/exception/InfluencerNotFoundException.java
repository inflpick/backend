package com.leesh.inflpick.v2.influencer.application.exception;

public class InfluencerNotFoundException extends IllegalArgumentException {
    public InfluencerNotFoundException(String message) {
        super(message);
    }
}
