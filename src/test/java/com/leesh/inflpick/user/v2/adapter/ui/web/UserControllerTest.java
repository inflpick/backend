package com.leesh.inflpick.user.v2.adapter.ui.web;

import com.leesh.inflpick.user.v2.adapter.ui.web.dto.WebOffsetPageResponse;
import com.leesh.inflpick.user.v2.adapter.ui.web.dto.response.UserWebResponse;
import com.leesh.inflpick.user.v2.core.application.UserService;
import com.leesh.inflpick.user.v2.mock.FakeUserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("어댑터 테스트 : UserController")
class UserControllerTest {

    UserController sut;

    @DisplayName("유저 페이지 조회 테스트")
    @Test
    void getUserPage() {

        // given
        Integer page = 0;
        Integer size = 20;
        String[] sort = {"createdDate,asc"};

        // when
        FakeUserRepository repository = FakeUserRepository.create();
        UserService service = UserService.builder()
                .queryUserPort(repository)
                .commandUserPort(repository)
                .build();
        sut = new UserController(service);
        ResponseEntity<WebOffsetPageResponse<UserWebResponse>> response = sut.getUserPage(page, size, sort);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(response.getBody()).isNotNull();
        UserWebResponse[] contents = response.getBody().getContents();
        assertThat(contents).isNotNull();
        assertThat(contents.length).isEqualTo(20);
    }
}