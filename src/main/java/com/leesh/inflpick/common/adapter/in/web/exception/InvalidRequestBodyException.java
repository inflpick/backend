package com.leesh.inflpick.common.adapter.in.web.exception;

import com.leesh.inflpick.common.domain.ErrorCode;
import org.springframework.http.HttpStatus;

public class InvalidRequestBodyException extends RuntimeException implements ErrorCode {

  public InvalidRequestBodyException() {
    super("요청 본문이 올바르지 않아요.");
  }

  @Override
  public String getCode() {
    return "INVALID_REQUEST_BODY";
  }

  @Override
  public HttpStatus getHttpStatus() {
    return HttpStatus.BAD_REQUEST;
  }

  @Override
  public String getReason() {
    return "요청 본문이 올바르지 않아요.";
  }

  @Override
  public String getAction() {
    return "요청 본문을 확인 후 다시 요청해주세요.";
  }

  @Override
  public String getComment() {
    return "API 요청 본문이 올바르지 않은 경우에 발생합니다. (ex. Json 형식이 올바르지 않은 경우)";
  }
}
