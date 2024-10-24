package com.leesh.inflpick.v2.application.service.user;

import com.leesh.inflpick.v2.adapter.out.persistence.mongo.user.FakeUserRepository;
import com.leesh.inflpick.v2.user.application.dto.UserCommand;
import com.leesh.inflpick.v2.user.application.service.CommandUserService;
import com.leesh.inflpick.v2.user.domain.vo.Nickname;
import com.leesh.inflpick.v2.user.domain.vo.Oauth2Info;
import com.leesh.inflpick.v2.user.domain.vo.Oauth2Provider;
import com.leesh.inflpick.v2.user.domain.vo.UserId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CommandUserServiceTest {


    CommandUserService sut;

    @BeforeEach
    void setUp() {
        FakeUserRepository userRepository = FakeUserRepository.create();
        sut = new CommandUserService(userRepository, userRepository);
    }

    @DisplayName("SocialUserRequest 객체로 유저를 생성할 수 있다.")
    @Test
    void testCreate() {

        // given
        Nickname nickname = Nickname.create("nickname");
        String oauth2Id = "oauth2Id";
        Oauth2Info oauth2Info = Oauth2Info.create(oauth2Id, Oauth2Provider.KAKAO);

        UserCommand request = UserCommand.builder(nickname, oauth2Info)
                .build();

        // when
        UserId createUserId = sut.create(request);

        // then
        Assertions.assertNotNull(createUserId);

    }

}