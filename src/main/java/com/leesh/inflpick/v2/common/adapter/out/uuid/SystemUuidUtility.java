package com.leesh.inflpick.v2.common.adapter.out.uuid;

import com.leesh.inflpick.v2.common.application.port.out.uuid.UuidPort;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SystemUuidUtility implements UuidPort {

    @Override
    public String uuid() {
        return UUID.randomUUID().toString();
    }
}
