package com.leesh.inflpick.common.adapter.out.persistence;

public class ResourceNotFoundException extends RuntimeException {

    private ResourceNotFoundException() {
        // 기본 생성자를 private 으로 선언하여 외부에서 호출할 수 없도록 함
    }

    public ResourceNotFoundException(String id, String resource) {
        super("ID %s에 해당하는 %s를 찾을 수 없습니다.".formatted(id, resource));
    }
}
