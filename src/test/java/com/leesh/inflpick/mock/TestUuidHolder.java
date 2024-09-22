package com.leesh.inflpick.mock;

import com.leesh.inflpick.common.port.out.UuidHolder;
import lombok.RequiredArgsConstructor;

public class TestUuidHolder implements UuidHolder {

    private final String uuid;

    public TestUuidHolder(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String uuid() {
        return uuid;
    }
}
