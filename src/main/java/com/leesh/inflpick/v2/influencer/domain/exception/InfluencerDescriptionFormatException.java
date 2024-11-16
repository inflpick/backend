package com.leesh.inflpick.v2.influencer.domain.exception;

import com.leesh.inflpick.v2.common.domain.ErrorCode;
import org.springframework.http.HttpStatus;

public class InfluencerDescriptionFormatException extends RuntimeException implements ErrorCode {

  public InfluencerDescriptionFormatException() {
    super("InfluencerDescription must be between 1 and 50000 characters long");
  }

  public InfluencerDescriptionFormatException(String description) {
    super("InfluencerDescription must be between 1 and 50000 characters long" + description.length());
  }

  @Override
  public String getCode() {
    return "INFLUENCER_DESCRIPTION_FORMAT";
  }

  @Override
  public HttpStatus getHttpStatus() {
    return HttpStatus.BAD_REQUEST;
  }

  @Override
  public String getReason() {
    return "인플루언서 설명은 1자 이상 50000자 이하로 입력해주세요.";
  }

  @Override
  public String getAction() {
    return "인플루언서 설명을 다시 입력해주세요.";
  }

  @Override
  public String getComment() {
    return "인플루언서 설명이 1자 이상 50000자 이하로 입력되지 않은 경우에 발생합니다.";
  }
}
