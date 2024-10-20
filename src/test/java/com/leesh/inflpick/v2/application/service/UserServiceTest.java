package com.leesh.inflpick.v2.application.service;

import com.leesh.inflpick.v2.appilcation.port.out.common.dto.OffsetPageRequest;
import com.leesh.inflpick.v2.appilcation.port.out.common.dto.OffsetPageResponse;
import com.leesh.inflpick.v2.appilcation.port.out.common.dto.SortCriterion;
import com.leesh.inflpick.v2.appilcation.port.out.common.dto.Sortable;
import com.leesh.inflpick.v2.appilcation.service.user.UserService;
import com.leesh.inflpick.v2.appilcation.port.out.user.dto.SocialUserRequest;
import com.leesh.inflpick.v2.domain.user.User;
import com.leesh.inflpick.v2.domain.user.vo.Nickname;
import com.leesh.inflpick.v2.domain.user.vo.Oauth2Info;
import com.leesh.inflpick.v2.domain.user.vo.Oauth2Provider;
import com.leesh.inflpick.v2.domain.user.vo.UserId;
import com.leesh.inflpick.v2.adapter.out.persistence.FakeUserRepository;
import com.leesh.inflpick.v2.appilcation.port.in.user.exception.DuplicatedSocialUserException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

@DisplayName("애플리케이션 테스트 : UserService")
class UserServiceTest {

    UserService sut;

    @BeforeEach
    void setUp() {
        FakeUserRepository userRepository = FakeUserRepository.create();
        sut = new UserService(userRepository, userRepository, () -> "uuid");
    }

    @DisplayName("SocialUserRequest 객체로 유저를 생성할 수 있다.")
    @Test
    void testCreate() {

        // given
        Nickname nickname = Nickname.create("nickname");
        String oauth2Id = "oauth2Id";
        Oauth2Info oauth2Info = Oauth2Info.create(oauth2Id, Oauth2Provider.KAKAO);
        UserId userId = UserId.create("userId");

        SocialUserRequest request = () -> User.builder(nickname, oauth2Info)
                .id(userId)
                .build();

        // when
        UserId createUserId = sut.create(request);

        // then
        Assertions.assertEquals(userId, createUserId);

    }

    @DisplayName("이미 등록된 소셜 계정이면, DuplicatedSocialUserException 예외를 던진다.")
    @Test
    void testAlreadyRegisteredSocialUser() {

        // given
        Nickname nickname = Nickname.create("nickname");
        UserId userId = UserId.create("userId");
        Oauth2Info oauth2Info = Oauth2Info.create("oauth2Id", Oauth2Provider.KAKAO);
        SocialUserRequest request = () -> User.builder(nickname, oauth2Info)
                .id(userId)
                .build();
        sut.create(request);

        // when
        Assertions.assertThrows(DuplicatedSocialUserException.class, () -> sut.create(request));
    }

    @DisplayName("페이지 요청을 받아서 페이지를 반환한다.")
    @Test
    void testGetPage() {

        // given
        Nickname nickname = Nickname.create("nickname");
        UserId userId = UserId.create("userId");
        Oauth2Info oauth2Info = Oauth2Info.create("oauth2Id", Oauth2Provider.KAKAO);
        SocialUserRequest request = () -> User.builder(nickname, oauth2Info)
                .id(userId)
                .build();
        sut.create(request);

        // when
        OffsetPageRequest pageRequest = new OffsetPageRequest() {
            @Override
            public Integer getPage() {
                return 0;
            }

            @Override
            public Integer getSize() {
                return 20;
            }

            @Override
            public Collection<SortCriterion> getSortCriteria(Sortable sortable) {
                return List.of();
            }
        };
        sut.getPage(pageRequest);

        // then
        OffsetPageResponse<User> page = sut.getPage(pageRequest);
        Assertions.assertNotNull(page);
        Assertions.assertEquals(0, page.getPage());
    }

}