package com.leesh.inflpick.v2.common.domain;

import org.springframework.http.HttpStatus;

public interface ErrorCode {

    String getCode();
    HttpStatus getHttpStatus();
    String getReason();
    String getAction();
    String getComment();

}
