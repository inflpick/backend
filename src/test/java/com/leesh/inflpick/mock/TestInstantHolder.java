package com.leesh.inflpick.mock;

import com.leesh.inflpick.common.adapter.out.time.InstantHolder;

import java.time.Instant;

public record TestInstantHolder(Instant instant) implements InstantHolder {
}
