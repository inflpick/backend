package com.leesh.inflpick.common.adapter.out.persistence;

import java.text.MessageFormat;

public class ResourceNotFoundException extends RuntimeException {

    public static final MessageFormat MESSAGE_FORMAT = new MessageFormat("UUID {0}에 해당하는 {1}를 찾을 수 없습니다.");

    private ResourceNotFoundException() {
        // 기본 생성자를 private 으로 선언하여 외부에서 호출할 수 없도록 함
    }

    public ResourceNotFoundException(String id, String resource) {
        super(MESSAGE_FORMAT.format(new Object[]{id, resource}));
    }
}
