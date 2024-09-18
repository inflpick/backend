package com.leesh.inflpick.common.adapter.out;

import com.leesh.inflpick.common.port.out.UuidHolder;
import org.springframework.stereotype.Component;

@Component
public class SystemUuidHolder implements UuidHolder {

    @Override
    public String uuid() {
        return java.util.UUID.randomUUID().toString();
    }
}
