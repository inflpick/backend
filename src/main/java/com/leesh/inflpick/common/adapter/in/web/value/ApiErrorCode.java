package com.leesh.inflpick.common.adapter.in.web.value;

import org.springframework.http.HttpStatus;

public interface ApiErrorCode {

    String code();
    HttpStatus httpStatus();
    String reason();
    String action();
    String comment();

}
