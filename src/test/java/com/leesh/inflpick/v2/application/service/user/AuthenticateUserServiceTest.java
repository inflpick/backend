package com.leesh.inflpick.v2.application.service.user;

import com.leesh.inflpick.v2.adapter.out.persistence.mongo.user.FakeUserRepository;
import com.leesh.inflpick.v2.user.application.service.AuthenticateUserService;
import com.leesh.inflpick.v2.user.domain.AuthenticationProcess;
import com.leesh.inflpick.v2.user.domain.User;
import com.leesh.inflpick.v2.user.domain.vo.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AuthenticateUserServiceTest {

    AuthenticateUserService sut;

    @DisplayName("인증을 시작하면, 유저의 인증 프로세스를 시작한다.")
    @Test
    void testCreate() {

        // given
        FakeUserRepository userRepository = FakeUserRepository.create();

        Nickname nickname = Nickname.create("nickname");
        String oauth2Id = "oauth2Id";
        Oauth2Info oauth2Info = Oauth2Info.create(oauth2Id, Oauth2Provider.KAKAO);
        UserId userId = UserId.create("userId");

        User user = User.builder(nickname, oauth2Info)
                .id(userId)
                .build();

        userRepository.save(user);
        String uuid = "uuid";
        sut = new AuthenticateUserService(userRepository, userRepository, () -> uuid);

        // when
        AuthenticationProcess process = sut.authenticate(userId);

        // then
        Assertions.assertNotNull(process);
        Assertions.assertEquals(process.getCode().value(), uuid);
        Assertions.assertEquals(process.getStatus(), AuthenticationStatus.IN_PROGRESS);

    }

}