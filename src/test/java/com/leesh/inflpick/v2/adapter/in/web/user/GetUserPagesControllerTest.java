package com.leesh.inflpick.v2.adapter.in.web.user;

import com.leesh.inflpick.v2.adapter.in.web.common.dto.WebOffsetPageResponse;
import com.leesh.inflpick.v2.adapter.in.web.user.dto.response.UserWebResponse;
import com.leesh.inflpick.v2.adapter.out.persistence.mongo.user.FakeUserRepository;
import com.leesh.inflpick.v2.appilcation.port.in.user.QueryUserUseCase;
import com.leesh.inflpick.v2.appilcation.port.in.user.exception.UserNotFoundException;
import com.leesh.inflpick.v2.appilcation.dto.common.OffsetPage;
import com.leesh.inflpick.v2.appilcation.dto.common.OffsetPageQuery;
import com.leesh.inflpick.v2.domain.user.User;
import com.leesh.inflpick.v2.domain.user.vo.AuthenticationCode;
import com.leesh.inflpick.v2.domain.user.vo.Oauth2Info;
import com.leesh.inflpick.v2.domain.user.vo.UserId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("어댑터 테스트 : UserController")
class GetUserPagesControllerTest {

    QueryUserController sut;

    @DisplayName("유저 페이지 조회 테스트")
    @Test
    void getUserPage() {

        // given
        Integer page = 0;
        Integer size = 20;
        String[] sort = {"createdDate,asc"};

        // when
        FakeUserRepository repository = FakeUserRepository.create();
        QueryUserUseCase service = new QueryUserUseCase() {
            @Override
            public User query(UserId userId) throws UserNotFoundException {
                return null;
            }

            @Override
            public User query(AuthenticationCode authenticationCode) throws UserNotFoundException {
                return null;
            }

            @Override
            public User query(Oauth2Info oauth2Info) throws UserNotFoundException {
                return null;
            }

            @Override
            public OffsetPage<User> query(OffsetPageQuery query) {
                return null;
            }
        };
        sut = new QueryUserController(service);
        ResponseEntity<WebOffsetPageResponse<UserWebResponse>> response = sut.query(page, size, sort);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(response.getBody()).isNotNull();
        UserWebResponse[] contents = response.getBody().getContents();
        assertThat(contents).isNotNull();
        assertThat(contents.length).isEqualTo(20);
    }
}