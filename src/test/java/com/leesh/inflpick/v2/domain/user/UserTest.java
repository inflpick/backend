package com.leesh.inflpick.v2.domain.user;

import com.leesh.inflpick.v2.domain.user.vo.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("도메인 테스트 : User")
class UserTest {

    @Test
    @DisplayName("인증 프로세스가 시작되면, 유저의 인증 상태는 IN_PROGRESS 가 되어야 한다.")
    void testStartAuthentication() {
        // given
        Nickname nickname = Nickname.create("testNickname");
        Oauth2Info oauth2Info = Oauth2Info.create("oauth2Id", Oauth2Provider.GOOGLE);
        User user = User.builder(nickname, oauth2Info).build();
        AuthenticationCode code = AuthenticationCode.create("1234");

        // when
        user.startAuthentication(code);

        // then
        assertTrue(user.isAuthenticationInProgress());
    }

    @Test
    @DisplayName("인증 프로세스를 완료하면, 유저의 인증 상태는 COMPLETED 가 되어야 한다.")
    void testCompleteAuthentication() {
        // given
        Nickname nickname = Nickname.create("testNickname");
        Oauth2Info oauth2Info = Oauth2Info.create("oauth2Id", Oauth2Provider.GOOGLE);
        User user = User.builder(nickname, oauth2Info).build();
        AuthenticationCode code = AuthenticationCode.create("1234");

        // when
        user.startAuthentication(code);
        user.completeAuthentication();

        // then
        assertEquals(AuthenticationStatus.COMPLETED, user.getAuthenticationProcess().getStatus());
    }

}