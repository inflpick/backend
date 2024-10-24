package com.leesh.inflpick.v2.shared.adapter.in.web;

import org.springframework.http.HttpStatus;

public interface ApiErrorCode {

    String getCode();
    HttpStatus getHttpStatus();
    String getReason();
    String getAction();
    String getComment();

}
