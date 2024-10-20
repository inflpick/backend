package com.leesh.inflpick.v2.adapter.out.persistence.mongo;

import com.leesh.inflpick.v2.adapter.out.persistence.user.UserDocument;
import com.leesh.inflpick.v2.domain.user.User;
import com.leesh.inflpick.v2.domain.user.vo.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("어댑터 테스트 : UserDocument")
public class UserDocumentTest {

    @Test
    @DisplayName("UserDocument 객체를 User 객체로 변환할 수 있다.")
    void testToEntity() {
        // given
        UserDocument userDocument = UserDocument.builder()
                .id("user123")
                .nickname("testNickname")
                .email("test@example.com")
                .profileImageUrl("http://example.com/profile.jpg")
                .role("USER")
                .oauth2Id("oauth2Id")
                .oauth2Provider("GOOGLE")
                .createdBy("creator")
                .createdDate(Instant.now())
                .lastModifiedBy("modifier")
                .lastModifiedDate(Instant.now())
                .build();

        // when
        User user = userDocument.toEntity();

        // then
        assertNotNull(user);
        assertEquals("user123", user.getId().value());
        assertEquals("testNickname", user.getNickname().value());
        assertEquals("test@example.com", user.getEmail().value());
        assertEquals("http://example.com/profile.jpg", user.getProfileImageUrl());
        assertEquals(Role.USER, user.getRole());
        assertEquals("oauth2Id", user.getOauth2Id());
        assertEquals(Oauth2Provider.GOOGLE, user.getOauth2Provider());
        assertEquals(AuthenticationStatus.NOT_STARTED, user.getAuthenticationProcess().getStatus());
        assertEquals(AuthenticationCode.empty(), user.getAuthenticationProcess().getCode());
    }

    @Test
    @DisplayName("User 객체를 UserDocument 객체로 변환할 수 있다.")
    void testFromEntity() {
        // given
        Nickname testNickname = Nickname.create("testNickname");
        Oauth2Info oauth2Id = Oauth2Info.create("oauth2Id", Oauth2Provider.GOOGLE);
        User user = User.builder(testNickname, oauth2Id)
                .email(UserEmail.create("test@example.com"))
                .profileImageUrl("http://example.com/profile.jpg")
                .role(Role.USER)
                .createdDate(Instant.now())
                .createdBy("creator")
                .lastModifiedDate(Instant.now())
                .lastModifiedBy("modifier")
                .build();

        // when
        UserDocument userDocument = UserDocument.from(user);

        // then
        assertNotNull(userDocument);
        assertEquals("testNickname", userDocument.getNickname());
        assertEquals("test@example.com", userDocument.getEmail());
        assertEquals("http://example.com/profile.jpg", userDocument.getProfileImageUrl());
        assertEquals(Role.USER.name(), userDocument.getRole());
        assertEquals("oauth2Id", userDocument.getOauth2Id());
        assertEquals("GOOGLE", userDocument.getOauth2Provider());
        assertEquals(AuthenticationStatus.NOT_STARTED.name(), userDocument.getAuthenticationStatus());
        assertEquals(AuthenticationCode.empty().value(), userDocument.getAuthenticationCode());
    }
}