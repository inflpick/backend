package com.leesh.inflpick.common.adapter.in.web.exception;

public class InvalidSortParameterException extends RuntimeException {

  public static final String ERROR_MESSAGE_FORMAT = "정렬 파라미터는 {정렬기준,방향}의 형식으로 입력해주세요.";

    public InvalidSortParameterException() {
        super(ERROR_MESSAGE_FORMAT);
    }
}
