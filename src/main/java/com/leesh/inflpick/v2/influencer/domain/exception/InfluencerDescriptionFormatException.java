package com.leesh.inflpick.v2.influencer.domain.exception;

public class InfluencerDescriptionFormatException extends IllegalArgumentException {

  public InfluencerDescriptionFormatException(String description) {
    super("InfluencerDescription must be between 1 and 50000 characters long" + description.length());
  }
}
