package com.leesh.inflpick.influencer.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

class ProfileTest {

    @Test
    @DisplayName("유효한 값으로 Profile 생성되어야 한다.")
    void profileCreationWithValidValues() throws URISyntaxException {
        URI imageUri = new URI("https://www.example.com/image.jpg");
        Profile profile = new Profile("John Doe", imageUri, "Description");
        assertNotNull(profile);
        assertEquals("John Doe", profile.name());
        assertEquals(imageUri, profile.profileImageUri());
        assertEquals("Description", profile.description());
    }

    @Test
    @DisplayName("null 이름으로 Profile 생성 시 예외가 발생해야 한다.")
    void profileCreationWithNullNameThrowsException() throws URISyntaxException {
        URI imageUri = new URI("https://www.example.com/image.jpg");
        assertThrows(NullPointerException.class, () -> {
            new Profile(null, imageUri, "Description");
        });
    }

    @Test
    @DisplayName("빈 이름으로 Profile 생성 시 예외가 발생해야 한다.")
    void profileCreationWithBlankNameThrowsException() throws URISyntaxException {
        URI imageUri = new URI("https://www.example.com/image.jpg");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Profile("", imageUri, "Description");
        });
        assertEquals("name must not be blank", exception.getMessage());
    }

    @Test
    @DisplayName("null 이미지 URI로 Profile 생성 시 예외가 발생해야 한다.")
    void profileCreationWithNullImageUriThrowsException() {
        assertThrows(NullPointerException.class, () -> {
            new Profile("John Doe", null, "Description");
        });
    }

    @Test
    @DisplayName("null 설명으로 Profile 생성 시 예외가 발생해야 한다.")
    void profileCreationWithNullDescriptionThrowsException() throws URISyntaxException {
        URI imageUri = new URI("https://www.example.com/image.jpg");
        assertThrows(NullPointerException.class, () -> {
            new Profile("John Doe", imageUri, null);
        });
    }

    @Test
    @DisplayName("빈 설명으로 Profile 생성 시 예외가 발생해야 한다.")
    void profileCreationWithBlankDescriptionThrowsException() throws URISyntaxException {
        URI imageUri = new URI("https://www.example.com/image.jpg");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Profile("John Doe", imageUri, "");
        });
        assertEquals("description must not be blank", exception.getMessage());
    }
}