package com.leesh.inflpick.common.adapter.out.uuid;

import com.leesh.inflpick.common.application.port.out.uuid.UuidPort;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SystemUuidUtility implements UuidPort {

    @Override
    public String uuid() {
        return UUID.randomUUID().toString();
    }
}
