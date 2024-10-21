package com.leesh.inflpick.v2.adapter.in.web.common.error;

import org.springframework.http.HttpStatus;

public interface ApiErrorCode {

    String getCode();
    HttpStatus getHttpStatus();
    String getReason();
    String getAction();
    String getComment();

}
