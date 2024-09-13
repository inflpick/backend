package com.leesh.inflpick.common.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class MetadataTest {

    @Test
    @DisplayName("유효한 값으로 Metadata 생성되어야 한다.")
    void metadataCreationWithValidValues() {
        Instant now = Instant.now();
        Metadata metadata = new Metadata("creator", now, "modifier", now);
        assertNotNull(metadata);
        assertEquals("creator", metadata.createdBy());
        assertEquals(now, metadata.createdAt());
        assertEquals("modifier", metadata.lastModifiedBy());
        assertEquals(now, metadata.lastModifiedAt());
    }

    @Test
    @DisplayName("null createdBy로 Metadata 생성 시 예외가 발생해야 한다.")
    void metadataCreationWithNullCreatedByThrowsException() {
        Instant now = Instant.now();
        assertThrows(NullPointerException.class, () -> {
            new Metadata(null, now, "modifier", now);
        });
    }

    @Test
    @DisplayName("null createdAt으로 Metadata 생성 시 예외가 발생해야 한다.")
    void metadataCreationWithNullCreatedAtThrowsException() {
        assertThrows(NullPointerException.class, () -> {
            new Metadata("creator", null, "modifier", Instant.now());
        });
    }

    @Test
    @DisplayName("null lastModifiedBy로 Metadata 생성 시 예외가 발생해야 한다.")
    void metadataCreationWithNullLastModifiedByThrowsException() {
        Instant now = Instant.now();
        assertThrows(NullPointerException.class, () -> {
            new Metadata("creator", now, null, now);
        });
    }

    @Test
    @DisplayName("null lastModifiedAt으로 Metadata 생성 시 예외가 발생해야 한다.")
    void metadataCreationWithNullLastModifiedAtThrowsException() {
        assertThrows(NullPointerException.class, () -> {
            new Metadata("creator", Instant.now(), "modifier", null);
        });
    }
}