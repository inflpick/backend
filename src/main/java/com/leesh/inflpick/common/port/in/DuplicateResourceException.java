package com.leesh.inflpick.common.port.in;

public class DuplicateResourceException extends RuntimeException {

    private DuplicateResourceException() {
        // 기본 생성자를 private 으로 선언하여 외부에서 호출할 수 없도록 함
    }

    public DuplicateResourceException(String field, String input, String resource) {
        super("%s에 %s를 가진 %s가 이미 존재합니다.".formatted(field, input, resource));
    }
}
